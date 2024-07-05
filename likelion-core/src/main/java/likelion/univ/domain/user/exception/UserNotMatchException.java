package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class UserNotMatchException extends BaseException {

    public UserNotMatchException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
