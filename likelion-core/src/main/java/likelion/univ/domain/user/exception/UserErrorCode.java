package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode{
    NOT_SUPPORTED_LOGIN_TYPE(BAD_REQUEST, "LOGIN_TYPE_400", "해당 방식은 지원하지않는 로그인 방식입니다."),
    USER_NOT_FOUND(NOT_FOUND, "USER_404", "유저를 찾을 수 없습니다."),
    EMAIL_ALREADY_REGISTERED(NOT_FOUND, "USER_409", "같은 이메일로 회원가입된 계정이 있습니다."),
    FORBIDDEN_AUTHORIZATION(FORBIDDEN, "ADMIN_404","접근 권한이 없습니다."),
    NO_DATA_FOUND(NOT_FOUND,"USER_404", "해당 페이지에 데이터가 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}