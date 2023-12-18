package likelion.univ.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.auth.usecase.*;
import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth")
@Tag(name = "Auth", description = "인증 API")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RequestIdTokenUseCase requestIdTokenUseCase;
    private final SignUpUseCase signUpUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final GetUserInfoUseCase getUserInfoUsecase;

    @Operation(summary = "id token 발급", description = "인가 코드로 id token을 발급받습니다.")
    @GetMapping("/{logintype}/idtoken")
    public SuccessResponse<Object> getIdToken(
            @RequestParam("code") String code,
            @PathVariable("logintype") String loginType){

        IdTokenDto idTokenDto = requestIdTokenUseCase.execute(loginType,code);
        return SuccessResponse.of(idTokenDto);
    }
    @Operation(summary = "id token 발급 (local용)", description = "인가 코드로 id token을 발급받습니다.(개발용으로 redirect url의 도메인이)" +
                                                                "localhostt:3000입니다. (나머지 경로는 같습니다.)")
    @GetMapping("/{logintype}/idtoken/local")
    public SuccessResponse<Object> getIdTokenForLocal(
            @RequestParam("code") String code,
            @PathVariable("logintype") String loginType){

        IdTokenDto idTokenDto = requestIdTokenUseCase.executeForLocal(loginType,code);
        return SuccessResponse.of(idTokenDto);
    }
    @Operation(summary = "로그인", description = "id token과 login type으로 로그인 합니다.")
    @PostMapping("/{logintype}/login")
    public SuccessResponse<Object> login(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType){

        AccountTokenDto accountTokenDto = loginUseCase.execute(loginType,idToken);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "회원가입", description = "id token과 login type으로 회원가입 합니다.")
    @PostMapping("/{logintype}/signup")
    public SuccessResponse<Object> signUp(
            @RequestParam("idtoken") String idToken,
            @PathVariable("logintype") String loginType,
            @RequestBody SignUpRequestDto signUpRequestDto){
        AccountTokenDto accountTokenDto = signUpUseCase.execute(loginType,idToken,signUpRequestDto);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "유저 정보 조회", description = "간단한 유저정보를 조회합니다.")
    @GetMapping("/userinfo")
    public SuccessResponse<Object> getUserInfo(){
        AccountUserInfoDto accountUserInfoDto = getUserInfoUsecase.execute();
        return SuccessResponse.of(accountUserInfoDto);
    }

    @Operation(summary = "토큰 재발급", description = "refresh token으로 access token을 재발급합니다.")
    @PostMapping("/refresh")
    public SuccessResponse<Object> refreshToken(
            @RequestParam("token") String refreshToken){
        AccountTokenDto accountTokenDto = refreshTokenUseCase.execute(refreshToken);
        return SuccessResponse.of(accountTokenDto);
    }
}
