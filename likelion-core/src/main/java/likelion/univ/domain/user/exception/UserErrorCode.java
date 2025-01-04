package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    NOT_SUPPORTED_LOGIN_TYPE(BAD_REQUEST, "LOGIN_TYPE_400", "해당 방식은 지원하지않는 로그인 방식입니다."),
    USER_NOT_MATCH(FORBIDDEN, "USER_403", "사용자가 일치하지 않습니다."),
    NOT_SUPER_ADMIN(FORBIDDEN, "USER_403_1", "총관리자가 아닙니다."),
    NO_ORDINAL_USER_ERROR(FORBIDDEN, "USER_403_1", "해당 유저의 기수정보가 존재하지 않습니다. 대학관리자 혹은 총관리자에게 문의해 주세요."),
    USER_NOT_FOUND(NOT_FOUND, "USER_404", "유저를 찾을 수 없습니다."),
    EMAIL_ALREADY_REGISTERED(NOT_FOUND, "USER_409", "같은 이메일로 회원가입된 계정이 있습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}