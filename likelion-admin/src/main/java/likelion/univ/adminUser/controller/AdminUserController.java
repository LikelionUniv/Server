package likelion.univ.adminUser.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.DeleteUserUseCase;
import likelion.univ.adminUser.usecase.FindUserUseCase;
import likelion.univ.adminUser.usecase.UpdateUserUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/univ")
@Api(tags = {"학교 대표"})
public class AdminUserController {

    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Operation(summary = "우리 학교 동아리 멤버 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<Object> findUsersByUniv(){
        Long userId = 2L;
        List<UserInfoResponseDto> response = findUserUseCase.findUsersByUnivOfUser(userId);
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 수정")
    @PatchMapping("/user/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.updateUser(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }


    @Operation(summary = "우리 학교 동아리 특정 멤버 삭제")
    @DeleteMapping("/user/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId){

        deleteUserUseCase.deleteUser(userId);
        return SuccessResponse.empty();
    }


}
