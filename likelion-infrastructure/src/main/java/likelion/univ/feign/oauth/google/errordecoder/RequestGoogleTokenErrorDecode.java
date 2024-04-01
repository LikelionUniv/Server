package likelion.univ.feign.oauth.google.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import likelion.univ.feign.exception.FeignClientException;
import likelion.univ.feign.oauth.google.dto.GoogleErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RequestGoogleTokenErrorDecode implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null) {
            try {
                String message = Util.toString(response.body().asReader(Util.UTF_8));
                GoogleErrorResponseDto errorResponseForm =
                        objectMapper.readValue(message, GoogleErrorResponseDto.class);
                return new FeignClientException(
                        response.status(),
                        methodKey,
                        errorResponseForm.getError(),
                        errorResponseForm.getErrorDescription()
                );
            } catch (IOException e) {
                log.error(methodKey + "Error Deserializing response body from failed feign request response.", e);
            }
        }
        return new FeignClientException(response.status(), methodKey, "GOOGLE_SERVER_ERROR", null);
    }
}
