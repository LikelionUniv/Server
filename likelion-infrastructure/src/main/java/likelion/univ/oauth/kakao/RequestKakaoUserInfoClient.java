package likelion.univ.oauth.kakao;

import likelion.univ.oauth.kakao.dto.KakaoUserInfoDto;
import likelion.univ.oauth.kakao.errordecoder.RequestKakaoUserInfoErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "RequestKakaoUserInfoClient",
        url = "https://kapi.kakao.com",
        configuration = RequestKakaoUserInfoErrorDecoder.class)
public interface RequestKakaoUserInfoClient {

    @GetMapping(
            "/v1/oidc/userinfo")
    KakaoUserInfoDto excute(
            @RequestHeader("Authorization") String accessToken);

}