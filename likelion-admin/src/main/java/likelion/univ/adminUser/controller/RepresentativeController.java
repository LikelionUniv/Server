package likelion.univ.adminUser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.DeleteUserUseCase;
import likelion.univ.adminUser.usecase.FindAllByUnivAdminUseCase;
import likelion.univ.adminUser.usecase.UpdateUserUseCase;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("adminUser.controller.RepresentativeController")
@RequestMapping(value = "/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "UserByUnivAdmin", description = "학교 대표에 의한 사용자 관련 API")
public class RepresentativeController {

    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllByUnivAdminUseCase findAllByUnivAdminUseCase;

    @Operation(summary = "우리 학교 동아리 멤버 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<Object> findUsersByUnivOfUser(Pageable pageable){
        SliceResponse<UserInfoResponseDto> response = findAllByUnivAdminUseCase.execute(pageable);
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 수정")
    @PatchMapping("/users/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.execute(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 삭제")
    @DeleteMapping("/users/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId){

        deleteUserUseCase.execute(userId);
        return SuccessResponse.empty();
    }


}
