package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.usecase.processor.RequestIdTokenProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class LoginUseCase {
    private final RequestIdTokenProcessor requestIdTokenProcessor;

    public AccountTokenDto login(String loginType, String code){
        String idToken = requestIdTokenProcessor.execute(loginType,code);
        log.info(idToken);
        AccountTokenDto accountTokenDto = AccountTokenDto.builder()
                .refreshToken("1")
                .accessToken("1")
                .build();
        return accountTokenDto;
    }

}
