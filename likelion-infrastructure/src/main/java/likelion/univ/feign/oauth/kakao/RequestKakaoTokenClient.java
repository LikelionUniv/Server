package likelion.univ.feign.oauth.kakao;

import likelion.univ.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.feign.oauth.kakao.errordecoder.RequestKakaoTokenErrorDecoder;
import likelion.univ.feign.oauth.oidc.PublicKeysDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(
        name = "RequestKakaoTokenClient",
        url = "https://kauth.kakao.com",
        configuration = RequestKakaoTokenErrorDecoder.class)
public interface RequestKakaoTokenClient {
    @PostMapping("/oauth/token?grant_type=authorization_code")
    KakaoTokenInfoDto getToken(
            @PathVariable("client_id") String clientId,
            @PathVariable("redirect_uri") String redirectUri,
            @PathVariable("code") String code,
            @PathVariable("client_secret") String client_secret);

    @GetMapping("/.well-known/jwks.json")
    PublicKeysDto getPublicKeys();
}
