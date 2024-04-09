package likelion.univ.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.auth.service.ClientAuthService;
import likelion.univ.auth.service.LocalClientAuthService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth")
@Tag(name = "Auth", description = "인증 API")
public class AuthController {

    private final ClientAuthService clientAuthService;
    private final LocalClientAuthService localClientAuthService;

    @Operation(summary = "id token 발급", description = "인가 코드로 id token을 발급받습니다.")
    @GetMapping("/{logintype}/idtoken")
    public SuccessResponse<IdTokenDto> getIdToken(
            @RequestParam("code") String code,
            @PathVariable("logintype") String loginType
    ) {
        IdTokenDto idTokenDto = clientAuthService.requestIdToken(loginType, code);
        return SuccessResponse.of(idTokenDto);
    }

    @Operation(
            summary = "id token 발급 (local용)",
            description = "인가 코드로 id token을 발급받습니다.(개발용으로 redirect url의 도메인이)" +
                          "localhost:3000입니다. (나머지 경로는 같습니다.)"
    )
    @GetMapping("/{loginType}/idToken/local")
    public SuccessResponse<Object> getIdTokenForLocal(
            @RequestParam("code") String code,
            @PathVariable("loginType") String loginType
    ) {
        IdTokenDto idTokenDto = localClientAuthService.requestIdTokenForLocal(loginType, code);
        return SuccessResponse.of(idTokenDto);
    }

    @Operation(summary = "로그인", description = "id token과 login type으로 로그인 합니다.")
    @PostMapping("/{logintype}/login")
    public SuccessResponse<Object> login(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType
    ) {
        AccountTokenDto accountTokenDto = clientAuthService.login(loginType, idToken);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "회원가입", description = "id token과 login type으로 회원가입 합니다.")
    @PostMapping("/{logintype}/signup")
    public SuccessResponse<Object> signUp(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType,
            @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        AccountTokenDto accountTokenDto = clientAuthService.signup(loginType, idToken, signUpRequestDto);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "유저 정보 조회", description = "간단한 유저정보를 조회합니다.")
    @GetMapping("/userInfo")
    public SuccessResponse<Object> getUserInfo() {
        AccountUserInfoDto accountUserInfoDto = clientAuthService.getUserInfo();
        return SuccessResponse.of(accountUserInfoDto);
    }

    @Operation(summary = "토큰 재발급", description = "refresh token으로 access token을 재발급합니다.")
    @PostMapping("/refresh")
    public SuccessResponse<Object> refreshToken(
            @RequestParam("token") String refreshToken
    ) {
        AccountTokenDto accountTokenDto = clientAuthService.refreshToken(refreshToken);
        return SuccessResponse.of(accountTokenDto);
    }
}
