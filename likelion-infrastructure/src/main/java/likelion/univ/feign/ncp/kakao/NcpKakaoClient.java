package likelion.univ.feign.ncp.kakao;

import likelion.univ.feign.ncp.NcpConfig;
import likelion.univ.feign.ncp.kakao.dto.FeignNCPKakaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NcpKakaoClient", url = "https://sens.apigw.fin-ntruss.com", configuration = NcpConfig.class)
public interface NcpKakaoClient {
    @PostMapping(path="/alimtalk/v2/services/{serviceId}/messages", consumes="application/json; charset=UTF-8")
    FeignNCPKakaoDto feignNCPKakaoDto(@PathVariable("serviceId")String serviceId,
                                      @RequestHeader("Content-Type")String contentType,
                                      @RequestHeader("x-ncp-apigw-timestamp") String timeStamp,
                                      @RequestHeader("x-ncp-iam-access-key") String accessKey,
                                      @RequestHeader("x-ncp-apigw-signature-v2") String signature,
                                      @RequestBody Object body);
}