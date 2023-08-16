package likelion.univ.api.oauth.kakao;

import likelion.univ.api.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.api.oauth.kakao.errordecoder.RequestKakaoTokenErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(
        name = "RequestKakaoTokenClient",
        url = "https://kauth.kakao.com",
        configuration = RequestKakaoTokenErrorDecoder.class)
@EnableFeignClients()
public interface RequestKakaoTokenClient {
    @PostMapping(
            "/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&client_secret={CLIENT_SECRET}")
    KakaoTokenInfoDto excute(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code,
            @PathVariable("CLIENT_SECRET") String client_secret);

}
