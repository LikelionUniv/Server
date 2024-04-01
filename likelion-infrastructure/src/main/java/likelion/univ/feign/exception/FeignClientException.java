package likelion.univ.feign.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeignClientException extends RuntimeException {

    private int httpStatus;
    private String methodKey;
    private String code;
    private String message;
}
