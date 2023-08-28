package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.usecase.processor.LoginByIdTokenProcessor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final JwtProvider jwtProvider;
    private final UserAdaptor userAdaptor;
    private final RefreshTokenRedisService refreshTokenRedisService;

    public AccountTokenDto execute(String loginType, String idToken){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            return AccountTokenDto.notRegistered();
        }
        User user = userAdaptor.findByEmail(userInfo.getEmail());
        if(user.getAuthInfo().getLoginType().getValue().equals(loginType)
                && user.getAuthInfo().getOid().equals(userInfo.getSub())){
            return issueRegisteredToken(user.getId(),
                    user.getAuthInfo(). getRole().getValue());
        }else throw new EmailAlreadyRegistered();
    }

    private AccountTokenDto issueRegisteredToken(Long userId, String role){
        String accessToken = jwtProvider.generateAccessToken(userId,role);
        String refreshToken = jwtProvider.generateRefreshToken(userId,role);
        /* refreshToken 캐쉬에 저장 */
        refreshTokenRedisService.save(userId, refreshToken);
        return AccountTokenDto.of(accessToken,refreshToken);
    }

}
