package likelion.univ.adminapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    /**
     * 400 BAD_REQUEST
     */
    FIELD_REQUIRED(BAD_REQUEST, "입력은 필수 입니다."),

    // 형식
    EMAIL_CHARACTER_INVALID(BAD_REQUEST, "올바른 형식의 이메일이 아닙니다."),
    PASSWORD_CHARACTER_INVALID(BAD_REQUEST, "올바른 형식의 비밀번호가 아닙니다."),
    ROLE_CHARACTER_INVALID(BAD_REQUEST, "올바른 권한이 아닙니다."),
    // 사이즈
    PASSWORD_LENGTH_INVALID(BAD_REQUEST, "비밀번호는 8~15자 이내여야 합니다."),

    /**
     * 401 UNAUTHORIZED
     */
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "로그인이 필요합니다."),

    /**
     * 403 FORBIDDEN
     */
    FORBIDDEN_RESOURCES(FORBIDDEN, "접근 권한이 없습니다."),

    /**
     * 404 NOT_FOUND
     */
    EMAIL_NOT_FOUND(NOT_FOUND, "등록된 이메일이 없습니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "등록된 멤버가 없습니다."),

    /**
     * 409 CONFLICT
     */
    DUPLICATE_EMAIL(CONFLICT, "이미 등록된 이메일 입니다."),

    /**
     * 500 SERVER_ERROR
     */
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

