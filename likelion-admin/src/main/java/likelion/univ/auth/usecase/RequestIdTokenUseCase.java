package likelion.univ.auth.usecase;


import likelion.univ.annotation.UseCase;
import likelion.univ.feign.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestIdTokenUseCase {
    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final KakaoProperties kakaoProperties;
    public IdTokenDto execute(String loginType, String code){
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl(),
                        code,
                        kakaoProperties.getClientSecret());
                return IdTokenDto.of(kakaoTokenInfoDto.getIdToken());
            case "google":
                break;
        }
        throw new NotSupportedLoginTypeException();
    }
}
