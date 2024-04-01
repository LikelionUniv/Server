package likelion.univ.email.exception;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailErrorCode implements BaseErrorCode {
    
    EMAIL_SEND_FAILED(BAD_REQUEST, "EMAIL_400", "이메일 전송에 실패하였습니다."),
    INVALID_ATTACHMENT(BAD_REQUEST, "EMAIL_401", "올바르지 않은 이메일 첨부파일 입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
