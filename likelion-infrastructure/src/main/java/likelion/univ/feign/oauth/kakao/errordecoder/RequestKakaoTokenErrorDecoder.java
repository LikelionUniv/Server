package likelion.univ.feign.oauth.kakao.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import likelion.univ.feign.exception.FeignClientException;
import likelion.univ.feign.oauth.kakao.dto.KakaoErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
public class RequestKakaoTokenErrorDecoder implements ErrorDecoder{
    private final ObjectMapper objectMapper;
    private final StringDecoder stringDecoder = new StringDecoder();
    @Override
    public Exception decode(String methodKey, Response response) {
            if (response.body() != null) {
                try {
                    String message = stringDecoder.decode(response, String.class).toString();
                    KakaoErrorResponseDto errorResponseForm= objectMapper.readValue(message, KakaoErrorResponseDto.class);
                    return new FeignClientException(response.status(), response.reason(),
                            errorResponseForm.getErrorCode(), errorResponseForm.getErrorDescription());
                } catch (IOException e) {
                    log.error(methodKey + "Error Deserializing response body from failed feign request response.", e);
                }
            }
            return new FeignClientException(response.status(), response.reason(), "KAKAO_SERVER_ERROR", null);
        }
}
