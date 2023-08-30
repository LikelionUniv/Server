package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.usecase.processor.GenerateAccountTokenProcessor;
import likelion.univ.auth.usecase.processor.LoginByIdTokenProcessor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.LoginType;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;

    public AccountTokenDto execute(String loginType, String idToken){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            return AccountTokenDto.notRegistered();
        }

        User user = userDomainService.login(LoginType.valueOf(loginType), userInfo.getEmail());
        return generateAccountTokenProcessor.execute(user);
    }
}
