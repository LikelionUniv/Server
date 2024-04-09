package likelion.univ.auth.service;

import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.feign.oauth.google.RequestGoogleTokenClient;
import likelion.univ.feign.oauth.google.dto.GoogleTokenInfoDto;
import likelion.univ.feign.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.properties.GoogleProperties;
import likelion.univ.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalClientAuthService {

    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final RequestGoogleTokenClient requestGoogleTokenClient;
    private final KakaoProperties kakaoProperties;
    private final GoogleProperties googleProperties;

    public IdTokenDto requestIdTokenForLocal(String loginType, String code) {
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrlForLocal(),
                        code,
                        kakaoProperties.getClientSecret());
                return IdTokenDto.of(kakaoTokenInfoDto.getIdToken());
            case "google":
                GoogleTokenInfoDto googleTokenInfoDto = requestGoogleTokenClient.getToken(
                        googleProperties.getClientId(),
                        googleProperties.getClientSecret(),
                        code,
                        googleProperties.getRedirectUrlForLocal());
                return IdTokenDto.of(googleTokenInfoDto.getIdToken());
        }
        throw new NotSupportedLoginTypeException();
    }
}
