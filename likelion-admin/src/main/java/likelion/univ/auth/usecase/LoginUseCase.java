package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.usecase.processor.LoginByIdTokenProcessor;
import likelion.univ.auth.usecase.processor.RequestIdTokenProcessor;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class LoginUseCase {
    private final RequestIdTokenProcessor requestIdTokenProcessor;
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;

    public AccountTokenDto login(String loginType, String code){
        String idToken = requestIdTokenProcessor.execute(loginType, code);
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        AccountTokenDto accountTokenDto = AccountTokenDto.builder()
                .accessToken(userInfo.getSub())
                .refreshToken("good")
                .build();

        return accountTokenDto;
    }

}
