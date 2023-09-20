package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class NotSupportedLoginTypeException extends BaseException {
    public NotSupportedLoginTypeException() {
        super(UserErrorCode.NOT_SUPPORTED_LOGIN_TYPE);
    }
}
