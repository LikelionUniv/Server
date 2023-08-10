package likelion.univ.auth.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.usecase.LoginUseCase;
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

//    @Operation(summary = "로그인 code를 통해 로그인을 진행합니다.")
//    @GetMapping("/login/{loginType}")
//    public AccountTokenDto getCredentialFromKaKao(
//            @RequestParam("code") String code,
//            @PathVariable("loginType") String loginType){
//
//        AccountTokenDto accountTokenDto = loginUseCase.excute(loginType,code);
//        return SuccessResponse.of(accountTokenDto);
//    }


}
