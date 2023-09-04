package likelion.univ.adminUser.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
@Api(tags = {"슈퍼 관리자"})
public class SuperAdminController {

    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUsersUseCase findUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindAllByUnivUseCase findAllByUnivUseCase;
    private final FindUserUseCase findUserUseCase;




    @Operation(summary = "멤버 전체 조회")
    @GetMapping("/superadmin/users")
    public SuccessResponse<Object> findAllUsers(int pageNum){

        List<UserInfoResponseDto> response = findUsersUseCase.excute(pageNum);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 조회")
    @GetMapping("/superadmin/{univ}/user/{userId}")
    public SuccessResponse<Object> findUserById(@PathVariable Long userId){
        UserInfoResponseDto response = findUserUseCase.excute(userId);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 학교 멤버 다건 조회")
    @GetMapping("/v1/univ/{univId}/users")
    public SuccessResponse<Object> findUserByUniv(@PathVariable Long univId){

        List<UserInfoResponseDto> response = findAllByUnivUseCase.excute(univId);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 수정")
    @PatchMapping("/superadmin/{univ}/user/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.excute(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 삭제")
    @DeleteMapping("/superadmin/{univ}/user/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId){

        UserInfoResponseDto response = deleteUserUseCase.excute(userId);
        return SuccessResponse.of(response);

}
    }
