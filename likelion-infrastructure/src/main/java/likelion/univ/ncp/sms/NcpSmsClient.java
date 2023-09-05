package likelion.univ.ncp.sms;

import likelion.univ.ncp.NcpConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NcpClient", url = "https://sens.apigw.ntruss.com", configuration = NcpConfig.class)
public interface NcpSmsClient {
    @PostMapping(path="/sms/v2/services/{serviceId}/messages", consumes="application/json; charset=UTF-8")
    FeignNCPSmsDto feignNCPSmsDto(@PathVariable("serviceId")String serviceId,
                                  @RequestHeader("Content-Type")String contentType,
                                  @RequestHeader("x-ncp-apigw-timestamp") String timeStamp,
                                  @RequestHeader("x-ncp-iam-access-key") String accessKey,
                                  @RequestHeader("x-ncp-apigw-signature-v2") String signature,
                                  @RequestBody Object body);
}
