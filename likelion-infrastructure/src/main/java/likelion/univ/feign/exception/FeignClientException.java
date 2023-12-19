package likelion.univ.feign.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeignClientException extends RuntimeException{
    private int httpStatus;
    private String reason;
    private String code;
    private String message;
}
