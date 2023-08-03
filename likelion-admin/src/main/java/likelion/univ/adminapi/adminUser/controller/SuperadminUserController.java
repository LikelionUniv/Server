package likelion.univ.adminapi.adminUser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import likelion.univ.adminapi.common.DefaultResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Schema(description = "SuperAdmin")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/superadmin")
public class SuperadminUserController {

    @Operation(description = "전체 회원 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "SUPERADMIN_USERS_FOUND",
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
    @GetMapping("v1/users")
    public ResponseEntity<DefaultResponseDto<Object>> foundAllUsers(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("SUPERADMIN_USERS_FOUND")
                        .responseMessage("전체 회원 조회 완료")
                        .data(null)
                        .build());
    }

    @Operation(description = "특정 멤버 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "SUPERADMIN_USER_FOUND",
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
    @GetMapping("v1/user/{userId}")
    public ResponseEntity<DefaultResponseDto<Object>> foundUser(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("SUPERADMIN_USER_FOUND")
                        .responseMessage("특정 멤버 조회 완료")
                        .data(null)
                        .build());
    }

    @Operation(description = "특정 학교 멤버 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "SUPERADMIN_UNIV_USERS_FOUND",
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
    @GetMapping("v1/{univ}/users")
    public ResponseEntity<DefaultResponseDto<Object>> foundUsersByUniv(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("SUPERADMIN_UNIV_USERS_FOUND")
                        .responseMessage("특정 학교 멤버 조회 완료")
                        .data(null)
                        .build());
    }

    @Operation(description = "특정 멤버 정보 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "SUPERADMIN_USER_UPDATED",
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
    @PutMapping("v1/{univ}/user/{userId}")
    public ResponseEntity<DefaultResponseDto<Object>> updateUser(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("SUPERADMIN_USER_UPDATED")
                        .responseMessage("특정 멤버 정보 수정 완료")
                        .build());
    }

    @Operation(description = "특정 멤버 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "SUPERADMIN_USER_DELETED",
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
    @DeleteMapping("v1/{univ}/user/{userId}")
    public ResponseEntity<DefaultResponseDto<Object>> deleteUser(
            HttpServletRequest servletRequest
    ) {


        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("SUPERADMIN_USER_DELETED")
                        .responseMessage("특정 멤버 삭제 완료")
                        .build());
    }
}
