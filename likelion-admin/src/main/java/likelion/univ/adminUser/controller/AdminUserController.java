package likelion.univ.adminUser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.DeleteUserUseCase;
import likelion.univ.adminUser.usecase.FindAllByUnivAdminUseCase;
import likelion.univ.adminUser.usecase.UpdateUserUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "UnivAdmin", description = "학교 대표 API")
public class AdminUserController {

    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllByUnivAdminUseCase findAllByUnivAdminUseCase;

    @Operation(summary = "우리 학교 동아리 멤버 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<Object> findUsersByUnivOfUser(Long univId){
        List<UserInfoResponseDto> response = findAllByUnivAdminUseCase.excute();
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 수정")
    @PatchMapping("/user/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.excute(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 삭제")
    @DeleteMapping("/user/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId){

        deleteUserUseCase.excute(userId);
        return SuccessResponse.empty();
    }


}
