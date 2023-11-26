package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.processor.GenerateAccountTokenProcessor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.exception.InvalidTokenException;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.jwt.dto.DecodedJwtToken;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;

import static likelion.univ.constant.StaticValue.REFRESH_TOKEN;

@UseCase
@RequiredArgsConstructor
public class RefreshTokenUseCase {
    private final RefreshTokenRedisService refreshTokenRedisService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;
    private final JwtProvider jwtProvider;
    private final UserAdaptor userAdaptor;
    public AccountTokenDto execute(String refreshToken){
        DecodedJwtToken decodedJwtToken = jwtProvider.decodeToken(refreshToken,REFRESH_TOKEN);
        User user = userAdaptor.findById(decodedJwtToken.getUserId());

        if(refreshTokenRedisService.checkToken(user.getId(), refreshToken)){
            return generateAccountTokenProcessor.refreshToken(user,refreshToken);
        }else throw new InvalidTokenException();
    }
}
