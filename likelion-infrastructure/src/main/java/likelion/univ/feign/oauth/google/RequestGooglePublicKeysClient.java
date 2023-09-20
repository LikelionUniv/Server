package likelion.univ.feign.oauth.google;

import likelion.univ.feign.oauth.google.errordecoder.RequestGoogleTokenErrorDecode;
import likelion.univ.feign.oauth.oidc.PublicKeysDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "RequestGooglePublicKeysClient",
        url = "https://www.googleapis.com/oauth2/v3/certs")
public interface RequestGooglePublicKeysClient {
    @GetMapping
    PublicKeysDto getPublicKeys();

}
