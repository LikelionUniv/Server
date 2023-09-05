package likelion.univ.adminUser.usecase;

import likelion.univ.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@UseCase
@Slf4j
@RequiredArgsConstructor
public class NcpKakaoUseCase {

   /* private final NcpKakaoClient ncpKakaoClient;
    private final UserAdaptor userAdaptor;

    @Value("${spring.ncp.sms.serviceId}")
    private String serviceId;
    @Value("${spring.ncp.sms.accessKey}")
    private String accessKey;
    @Value("${spring.ncp.sms.secretKey}")
    private String secretKey;

    public void sendNcpKakao(NcpRequestDto.SendKakaoRequestDto sendKakaoRequestDto) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeyException {
        //signature 생성
        String timestamp =String.valueOf(Instant.now().toEpochMilli());

        NcpRequestDto.NcpKakaoRequestDto body = NcpRequestDto.NcpKakaoRequestDto.builder()
                .plusFriendId()
                .templateCode()
                .messages(sendKakaoRequestDto)
                .build();

        ncpKakaoClient.feignNCPKakaoDto(
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
        String url = "/alimtalk/v2/services/"+this.serviceId+"/messages";

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

    }*/
}
