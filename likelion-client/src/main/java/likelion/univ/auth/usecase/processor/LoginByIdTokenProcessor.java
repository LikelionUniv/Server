package likelion.univ.auth.usecase.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.feign.oauth.oidc.PublicKeyDto;
import likelion.univ.feign.oauth.oidc.PublicKeysDto;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.exception.IncorrectIssuerTokenException;
import likelion.univ.jwt.JwtIdTokenProvider;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.properties.GoogleProperties;
import likelion.univ.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;


@Processor
@RequiredArgsConstructor
public class LoginByIdTokenProcessor {
    private final PublicKeyProcessor publicKeyProcessor;
    private final JwtIdTokenProvider jwtIdTokenProvider;

    private final KakaoProperties kakaoProperties;
    private final GoogleProperties googleProperties;

    public UserInfoFromIdToken execute(String loginType, String idToken){
        String kid = jwtIdTokenProvider.getKid(idToken);
        PublicKeysDto publicKeys = new PublicKeysDto();
        String iss = new String();
        String aud = new String();
        switch (loginType) {
            case "kakao":
                PublicKeysDto kakaoKeys = publicKeyProcessor.getCachedKakaoPublicKeys();
                publicKeys = kakaoKeys;
                iss = kakaoProperties.getIss();
                aud = kakaoProperties.getClientId();
                break;
            case "google":
                PublicKeysDto googleKeys = publicKeyProcessor.getCachedGooglePublicKeys();
                publicKeys = googleKeys;
                iss = googleProperties.getIss();
                aud = googleProperties.getClientId();

                break;
            default:
                throw new NotSupportedLoginTypeException();
        }
        PublicKeyDto key = publicKeys.getKeys().stream()
                .filter(k -> k.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new IncorrectIssuerTokenException());
        return jwtIdTokenProvider.getUserInfo(idToken, publicKeyProcessor.generatePublicKey(key), iss, aud);
    }

}
