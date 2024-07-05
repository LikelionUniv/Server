package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class NoOrdinalUserException extends BaseException {

    public NoOrdinalUserException() {
        super(UserErrorCode.NO_ORDINAL_USER_ERROR);
    }
}
