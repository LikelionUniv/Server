package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class NotAuthentiatedException extends BaseException {
    public NotAuthentiatedException() {
        super(SecurityErrorCode.NOT_AUTHENTIATED_ERROR);
    }
}
