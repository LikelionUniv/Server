package likelion.univ.auth.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.auth.usecase.LoginUseCase;
import likelion.univ.auth.usecase.RefreshTokenUseCase;
import likelion.univ.auth.usecase.RequestIdTokenUseCase;
import likelion.univ.auth.usecase.SignUpUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth")
@Api(tags = {"인증 및 로그인 API"})
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RequestIdTokenUseCase requestIdTokenUseCase;
    private final SignUpUseCase signUpUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @Operation(summary = "id token을 발급받습니다.")
    @GetMapping("/{logintype}/idtoken")
    public SuccessResponse<Object> getIdToken(
            @RequestParam("code") String code,
            @PathVariable("logintype") String loginType){

        IdTokenDto idTokenDto = requestIdTokenUseCase.execute(loginType,code);
        return SuccessResponse.of(idTokenDto);
    }
    @Operation(summary = "id token으로 로그인 합니다.")
    @PostMapping("/{logintype}/login")
    public SuccessResponse<Object> login(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType){

        AccountTokenDto accountTokenDto = loginUseCase.execute(loginType,idToken);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "id token으로 회원가입 합니다.")
    @PostMapping("/{logintype}/signup")
    public SuccessResponse<Object> signUp(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType,
            @RequestBody SignUpRequestDto signUpRequestDto){

        AccountTokenDto accountTokenDto = signUpUseCase.execute(loginType,idToken,signUpRequestDto);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "refresh token으로 access token을 재발급합니다.")
    @PostMapping("/refresh")
    public SuccessResponse<Object> refreshToken(
            @RequestParam("token") String refreshToken){

        AccountTokenDto accountTokenDto = refreshTokenUseCase.execute(refreshToken);
        return SuccessResponse.of(accountTokenDto);
    }
}
