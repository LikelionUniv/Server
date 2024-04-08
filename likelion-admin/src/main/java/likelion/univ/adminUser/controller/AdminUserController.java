package likelion.univ.adminUser.controller;

import static org.springframework.data.domain.Sort.Direction.DESC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.adminUser.dto.request.DeletedUserListRequestDto;
import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.DeleteAllUserUsecase;
import likelion.univ.adminUser.usecase.DeleteUserUsecase;
import likelion.univ.adminUser.usecase.FindAllByUnivAdminUsecase;
import likelion.univ.adminUser.usecase.UpdateUserUsecase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminUser.controller.RepresentativeController")
@RequestMapping("/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "UserByUnivAdmin", description = "학교 대표에 의한 사용자 관련 API")
public class AdminUserController {

    private final UpdateUserUsecase updateUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final DeleteAllUserUsecase deleteAllUserUsecase;
    private final FindAllByUnivAdminUsecase findAllByUnivAdminUsecase;

    @Operation(summary = "우리 학교 동아리 멤버 전체 조회")
    @GetMapping("/univ/users")
    public SuccessResponse<PageResponse<UserInfoResponseDto>> findUsersByUnivOfUser(
            @ParameterObject
            @PageableDefault(size = 10, page = 0, sort = "createdDate", direction = DESC)
            Pageable pageable
    ) {
        PageResponse<UserInfoResponseDto> response = findAllByUnivAdminUsecase.execute(pageable);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "우리 학교 동아리 특정 멤버 수정")
    @PatchMapping("/users/{userId}")
    public SuccessResponse<UserInfoResponseDto> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserRequestDto updateUserRequestDto
    ) {
        UserInfoResponseDto response = updateUserUsecase.execute(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "우리 학교 동아리 특정 멤버 삭제")
    @DeleteMapping("/users/{userId}")
    public SuccessResponse deleteUser(
            @PathVariable("userId") Long userId
    ) {
        deleteUserUsecase.execute(userId);
        return SuccessResponse.empty();
    }

    @Operation(summary = "우리 학교 동아리 멤버(리스트) 삭제")
    @DeleteMapping("/users")
    public SuccessResponse deleteUserList(
            @Valid @RequestBody DeletedUserListRequestDto deletedUserListRequestDto
    ) {
        deleteAllUserUsecase.execute(deletedUserListRequestDto);
        return SuccessResponse.empty();
    }
}
