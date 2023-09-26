package likelion.univ.feign.ncpSms;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SendNcpSms {

    private final NcpSmsClient ncpSmsClient;

    @Value("${ncp.sms.serviceId}")
    private String serviceId;
    @Value("${ncp.sms.accessKey}")
    private String accessKey;
    @Value("${ncp.sms.secretKey}")
    private String secretKey;;
    @Value("${ncp.sms.phoneNum}")
    private String phoneNum;

    public void sendNcpSms(String content, List<String> toPhoneNums)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        //signature 생성
        String timestamp =String.valueOf(Instant.now().toEpochMilli());
        List<NcpSmsContent.PhoneNum> toPhoneNumList = new ArrayList<>();

        for(int i=0;i<toPhoneNums.size();i++){
            toPhoneNumList.add(
                    NcpSmsContent.PhoneNum.builder()
                            .to(toPhoneNums.get(i))
                            .build()
            );
        }

        NcpSmsContent.NcpSmsRequestDto body = NcpSmsContent.NcpSmsRequestDto.builder()
                .type("SMS")
                .contentType("COMM") //AD(080 거부 신청해야함) or COMM
                .countryCode("82")
                .from(phoneNum)
                .content(content)
                .messages(toPhoneNumList)
                .build();

        ncpSmsClient.ncpSmsDto(
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
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
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
