package likelion.univ.auth.usecase;

import static likelion.univ.constant.StaticValue.REFRESH_TOKEN;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.processor.GenerateAccountTokenProcessor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.exception.ExpiredTokenException;
import likelion.univ.exception.InvalidTokenException;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.jwt.dto.DecodedJwtToken;
import likelion.univ.refreshtoken.exception.ExpiredRefreshTokenException;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RefreshTokenUsecase {

    private final RefreshTokenRedisService refreshTokenRedisService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;
    private final JwtProvider jwtProvider;
    private final UserAdaptor userAdaptor;

    public AccountTokenDto execute(String refreshToken) {
        DecodedJwtToken decodedJwtToken = decodeRefreshToken(refreshToken);
        User user = userAdaptor.findById(decodedJwtToken.getUserId());

        if (refreshTokenRedisService.checkToken(user.getId(), refreshToken)) {
            return generateAccountTokenProcessor.refreshToken(user, refreshToken);
        } else {
            throw new InvalidTokenException();
        }
    }

    private DecodedJwtToken decodeRefreshToken(String refreshToken) {
        try {
            return jwtProvider.decodeToken(refreshToken, REFRESH_TOKEN);
        } catch (ExpiredTokenException e) {
            throw new ExpiredRefreshTokenException();
        }
    }
}
