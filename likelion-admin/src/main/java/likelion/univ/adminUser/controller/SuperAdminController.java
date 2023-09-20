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
@RequestMapping(value = "/v1/superAdmin")
@Api(tags = {"슈퍼 관리자"})
public class SuperAdminController {

    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindAllByUnivUseCase findAllByUnivUseCase;
    private final FindUserUseCase findUserUseCase;




    @Operation(summary = "멤버 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<Object> findAllUser(int pageNum){

        List<UserInfoResponseDto> response = findAllUserUseCase.excute(pageNum);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 조회")
    @GetMapping("/user/{userId}")
    public SuccessResponse<Object> findUserById(@PathVariable Long userId){
        UserInfoResponseDto response = findUserUseCase.excute(userId);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 학교 멤버 다건 조회")
    @GetMapping("/univ/{univName}/users")
    public SuccessResponse<Object> findUsersByUniv(@PathVariable String univName){

        List<UserInfoResponseDto> response = findAllByUnivUseCase.excute(univName);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 수정")
    @PatchMapping("/{univId}/user/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @PathVariable("univId")Long univId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.excute(userId,univId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 삭제")
    @DeleteMapping("/{univId}/user/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId,
                                              @PathVariable("univId")Long univId){

        UserInfoResponseDto response = deleteUserUseCase.excute(userId,univId);
        return SuccessResponse.of(response);

}
    }
