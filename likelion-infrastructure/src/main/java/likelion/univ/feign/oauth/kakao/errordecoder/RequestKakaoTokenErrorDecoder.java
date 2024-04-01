package likelion.univ.feign.oauth.kakao.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import likelion.univ.feign.exception.FeignClientException;
import likelion.univ.feign.oauth.kakao.dto.KakaoErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class RequestKakaoTokenErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null) {
            try {
                String message = Util.toString(response.body().asReader(Util.UTF_8));
                KakaoErrorResponseDto errorResponseForm =
                        objectMapper.readValue(message, KakaoErrorResponseDto.class);
                return new FeignClientException(
                        response.status(),
                        methodKey,
                        errorResponseForm.getErrorCode(),
                        errorResponseForm.getErrorDescription()
                );
            } catch (IOException e) {
                log.error(methodKey + "Error Deserializing response body from failed feign request response.", e);
            }
        }
        return new FeignClientException(response.status(), methodKey, "KAKAO_SERVER_ERROR", null);
    }
}
