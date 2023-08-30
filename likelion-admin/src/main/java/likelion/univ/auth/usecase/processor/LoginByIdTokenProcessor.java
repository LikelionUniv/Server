package likelion.univ.auth.usecase.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.api.oauth.oidc.PublicKeyDto;
import likelion.univ.api.oauth.oidc.PublicKeysDto;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.exception.IncorrectIssuerTokenException;
import likelion.univ.jwt.JwtIdTokenProvider;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;


@Processor
@RequiredArgsConstructor
public class LoginByIdTokenProcessor {
    private final PublicKeyProcessor publicKeyProcessor;
    private final JwtIdTokenProvider jwtIdTokenProvider;

    private final KakaoProperties kakaoProperties;

    public UserInfoFromIdToken execute(String loginType, String idToken){
        String kid = jwtIdTokenProvider.getKid(idToken);
        PublicKeysDto publicKeys = new PublicKeysDto();
        String iss = new String();
        String aud = new String();
        switch (loginType) {
            case "kakao":
                PublicKeysDto keys = publicKeyProcessor.getCachedKakaoPublicKeys();
                publicKeys = keys;
                iss = kakaoProperties.getIss();
                aud = kakaoProperties.getAppKey();
                break;
            case "google":

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
