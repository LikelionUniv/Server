package likelion.univ.auth.usecase.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.domain.user.entity.User;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class GenerateAccountTokenProcessor {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;

    public AccountTokenDto createToken(User user){
        String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getAuthInfo().getRole().getValue());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getAuthInfo().getRole().getValue());
        /* refreshToken 캐쉬에 저장 */
        refreshTokenRedisService.save(user.getId(), refreshToken);
        return AccountTokenDto.of(accessToken,refreshToken);
    }

    public AccountTokenDto refreshToken(User user, String refreshToken){
        String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getAuthInfo().getRole().getValue());
        return AccountTokenDto.of(accessToken,refreshToken);
    }
}
