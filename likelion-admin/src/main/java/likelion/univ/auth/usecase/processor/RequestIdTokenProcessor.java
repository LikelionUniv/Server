package likelion.univ.auth.usecase.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.api.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.api.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class RequestIdTokenProcessor {
    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final KakaoProperties kakaoProperties;
    public String execute(String loginType, String code){
        String idToken = new String();
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl(),
                        code,
                        kakaoProperties.getClientSecret());
                idToken = kakaoTokenInfoDto.getIdToken();
                break;
            case "google":
                break;
            default:
                throw new NotSupportedLoginTypeException();
        }
        return idToken;
    }
}
