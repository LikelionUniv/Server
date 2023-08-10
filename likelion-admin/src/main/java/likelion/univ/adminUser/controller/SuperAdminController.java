package likelion.univ.adminUser.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.*;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/superadmin/")
@Api(tags = {"슈퍼 관리자"})
public class SuperAdminController {

    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindUnivUseCase findUnivUseCase;
    private final FindUsersByUnivUseCase findUsersByUnivUseCase;



    @Operation(summary = "멤버 전체 조회")
    @GetMapping("v1/users")
    public SuccessResponse<Object> findAllUsers(){

        List<UserInfoResponseDto> response = findAllUserUseCase.findAllUser();

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 조회")
    @GetMapping("v1/{univ}/user/{userId}")
    public SuccessResponse<Object> findUserById(@PathVariable @RequestParam("userId") Long userId){
        userId = 1L;

        UserInfoResponseDto response = findUserByIdUseCase.findById(userId);

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 학교 멤버 다건 조회")
    @GetMapping("v1/{univ}/users")
    public SuccessResponse<Object> findUserByUniv(@PathVariable @RequestParam("univ") String univ){

        University university = findUnivUseCase.findUniversity(univ);

        List<UserInfoResponseDto> response = findUsersByUnivUseCase.findUsersByUniv(university.getId());

        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 수정")
    @PatchMapping("v1/{univ}/user/{userId}")
    public SuccessResponse<Object> updateUser(@PathVariable("userId")Long userId,
                                              @RequestBody UpdateUserRequestDto updateUserRequestDto){

        UserInfoResponseDto response = updateUserUseCase.updateUser(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "특정 멤버 삭제")
    @DeleteMapping("v1/{univ}/user/{userId}")
    public SuccessResponse<Object> deleteUser(@PathVariable("userId")Long userId){

        UserInfoResponseDto response = deleteUserUseCase.deleteUser(userId);
        return SuccessResponse.of(response);

}
    }
