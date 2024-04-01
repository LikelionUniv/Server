package likelion.univ.feign.oauth.google;

import likelion.univ.feign.oauth.google.dto.GoogleTokenInfoDto;
import likelion.univ.feign.oauth.google.errordecoder.RequestGoogleTokenErrorDecode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "RequestGoogleTokenClient",
        url = "https://oauth2.googleapis.com",
        configuration = RequestGoogleTokenErrorDecode.class
)
public interface RequestGoogleTokenClient {
    @PostMapping("/token?grant_type=authorization_code")
    GoogleTokenInfoDto getToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("redirect_uri") String redirectUri
    );
}
