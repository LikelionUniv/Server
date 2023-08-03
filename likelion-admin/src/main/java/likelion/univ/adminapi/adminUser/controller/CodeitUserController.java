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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Schema(description = "Codeit")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/codeit")
public class CodeitUserController {

    @Operation(description = "게시판 배너 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "CODEIT_BOARDBANNER_UPDATED",
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
    @PatchMapping("v1/post/banner")
    public ResponseEntity<DefaultResponseDto<Object>> updateBoardBanner(
            HttpServletRequest servletRequest
            // TODO: requestDto 받기
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("CODEIT_BOARDBANNER_UPDATED")
                        .responseMessage("게시판 배너 수정 완료")
                        .build());
    }

    @Operation(description = "메신저(이메일, 카카오, 문자) 선택 발신")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "CODEIT_MESSAGER_SEND",
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
    @PostMapping("v1/notification")
    public ResponseEntity<DefaultResponseDto<Object>> sendMessangers(
            HttpServletRequest servletRequest
            // TODO: requestDto 받기
    ) {

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("CODEIT_MESSAGER_SEND")
                        .responseMessage("메신저(이메일, 카카오, 문자) 선택 발신 완료")
                        .build());
    }
}
