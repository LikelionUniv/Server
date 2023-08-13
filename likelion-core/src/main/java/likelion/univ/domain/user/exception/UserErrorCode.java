package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import static likelion.univ.constant.StaticValue.NOT_FOUND;
@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(NOT_FOUND, "USER_404", "유저가 존재하지 않습니다.");
    private final int httpStatus;
    private final String code;
    private final String message;
}
