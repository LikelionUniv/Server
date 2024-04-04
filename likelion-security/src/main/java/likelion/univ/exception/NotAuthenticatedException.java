package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class NotAuthenticatedException extends BaseException {

    public NotAuthenticatedException() {
        super(SecurityErrorCode.NOT_AUTHENTIATED_ERROR);
    }
}
