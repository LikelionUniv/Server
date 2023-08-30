package likelion.univ.auth.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.auth.usecase.LoginUseCase;
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

    @Operation(summary = "id token을 발급받습니다.")
    @GetMapping("/{loginType}/idToken")
    public SuccessResponse<Object> getIdToken(
            @RequestParam("code") String code,
            @PathVariable("loginType") String loginType){

        IdTokenDto idTokenDto = requestIdTokenUseCase.execute(loginType,code);
        return SuccessResponse.of(idTokenDto);
    }
    @Operation(summary = "id token으로 로그인 합니다.")
    @PostMapping("/{loginType}/login")
    public SuccessResponse<Object> login(
            @RequestParam("idToken") String idToken,
            @PathVariable("loginType") String loginType){

        AccountTokenDto accountTokenDto = loginUseCase.execute(loginType,idToken);
        return SuccessResponse.of(accountTokenDto);
    }

    @Operation(summary = "id token으로 회원가입 합니다.")
    @PostMapping("/{loginType}/register")
    public SuccessResponse<Object> register(
            @RequestParam("idToken") String idToken,
            @PathVariable("loginType") String loginType,
            @RequestBody SignUpRequestDto signUpRequestDto){

        AccountTokenDto accountTokenDto = signUpUseCase.execute(loginType,idToken,signUpRequestDto)
        return SuccessResponse.of();
    }


}
