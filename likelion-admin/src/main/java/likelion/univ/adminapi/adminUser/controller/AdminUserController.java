package likelion.univ.adminapi.adminUser.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import likelion.univ.adminapi.common.DefaultResponseDto;
import likelion.univ.adminapi.adminUser.service.ManageUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;

@Schema(description = "Admin")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/admin")
public class AdminUserController {

    private final ManageUserService manageUserService;

    @Operation(description = "우리 학교 동아리 멤버 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ADMIN_USERS_FOUND",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "403",
                    description = "FORBIDDEN_RESOURCES"),
            @ApiResponse(responseCode = "404",
                    description = "USER_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("v1/univ/users")
    public ResponseEntity<DefaultResponseDto<Object>> foundUsers(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("ADMIN_USERS_FOUND")
                        .responseMessage("우리 학교 동아리 멤버 전체 조회 완료")
                        .data(null)
                        .build());
    }

    @Operation(description = "우리 학교 동아리 특정 멤버 정보 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ADMIN_USER_UPDATED",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "403",
                    description = "FORBIDDEN_RESOURCES"),
            @ApiResponse(responseCode = "404",
                    description = "USER_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PutMapping("v1/univ/user/{userId}")
    public ResponseEntity<DefaultResponseDto<Object>> updateUser(
            HttpServletRequest servletRequest,
            @PathVariable("userId")
            @Positive(message = "회원 식별자는 양수만 가능합니다.")
            Long userId
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("ADMIN_USER_UPDATED")
                        .responseMessage("우리 학교 동아리 특정 멤버 정보 수정 완료")
                        .build());
    }

    @Operation(description = "우리 학교 동아리 특정 멤버 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ADMIN_USER_DELETED",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "403",
                    description = "FORBIDDEN_RESOURCES"),
            @ApiResponse(responseCode = "404",
                    description = "USER_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @DeleteMapping("v1/univ/user/{userId}")
    public ResponseEntity<DefaultResponseDto<Object>> deleteUser(
            HttpServletRequest servletRequest,
            @PathVariable("userId")
            @Positive(message = "회원 식별자는 양수만 가능합니다.")
            Long userId
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("ADMIN_USER_DELETED")
                        .responseMessage("우리 학교 동아리 특정 멤버 정보 삭제 완료")
                        .build());
    }

    @Operation(description = "선택된 회원 리쿠르팅 알림 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ADMIN_NOTIFICATION_USERS_FOUND",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "403",
                    description = "FORBIDDEN_RESOURCES"),
            @ApiResponse(responseCode = "404",
                    description = "USER_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("v1/univ/notification")
    public ResponseEntity<DefaultResponseDto<Object>> findRecruitingNotificationUsers(
            HttpServletRequest servletRequest
            // TODO: requestDto 받기
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("ADMIN_NOTIFICATION_USERS_FOUND")
                        .responseMessage("선택된 회원 리쿠르팅 알림 조회")
                        .build());
    }

    @Operation(description = "선택된 회원 리쿠르팅 알림 전송")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ADMIN_NOTIFICATION_USERS_SEND",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "403",
                    description = "FORBIDDEN_RESOURCES"),
            @ApiResponse(responseCode = "404",
                    description = "USER_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PostMapping("v1/univ/notification")
    public ResponseEntity<DefaultResponseDto<Object>> sendRecruitingNotificationUsers(
            HttpServletRequest servletRequest
            // TODO: requestDto 받기
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("ADMIN_NOTIFICATION_USERS_SEND")
                        .responseMessage("우리 학교 동아리 특정 멤버 정보 전송 완료")
                        .build());
    }
}
