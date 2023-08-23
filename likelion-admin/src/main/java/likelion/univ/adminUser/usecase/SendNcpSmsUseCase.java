package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.NcpSmsRequestDto;
import likelion.univ.adminUser.dto.request.SendMsgRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.sms.NcpSmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class SendNcpSmsUseCase {
    private final NcpSmsClient ncpSmsClient;

    @Value("${spring.ncp.sms.serviceId}")
    private String serviceId;
    @Value("${spring.ncp.sms.accessKey}")
    private String accessKey;
    @Value("${spring.ncp.sms.secretKey}")
    private String secretKey;;
    @Value("${spring.ncp.sms.phoneNum}")
    private String phoneNum;

    public void sendNcpSms(List<SendMsgRequestDto> sendMsgRequestDto) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeyException {
        //signature 생성
        String timestamp =String.valueOf(Instant.now().toEpochMilli());


        NcpSmsRequestDto body = NcpSmsRequestDto.builder()
                .type("SMS")
                .contentType("COMM") //AD(080 거부 신청해야함) or COMM
                .countryCode("82")
                .from(phoneNum)
                .content("왜 안되는거닝") //default 메시지
                .messages(sendMsgRequestDto)
                .build();

        ncpSmsClient.feignNCPSmsDto(
                serviceId,
                "application/json; charset=utf-8",
                timestamp,
                accessKey,
                makeSignature(timestamp),
                body

        );
    }



    public String makeSignature(String timestamp) throws NoSuchElementException, UnsupportedEncodingException,
            InvalidKeyException, NoSuchAlgorithmException {
        String space=" ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+this.serviceId+"/messages";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;

    }

}
