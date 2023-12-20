package likelion.univ.email.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum EmailErrorCode implements BaseErrorCode {

    EMAIL_SEND_FAILED(BAD_REQUEST, "EMAIL_400","이메일 전송에 실패하였습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
