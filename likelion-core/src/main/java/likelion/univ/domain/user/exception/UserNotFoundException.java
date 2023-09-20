package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
