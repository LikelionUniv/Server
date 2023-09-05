package likelion.univ.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class NotAuthentiatedException extends BaseException {
    public NotAuthentiatedException() {
        super(GlobalErrorCode.NOT_AUTHENTIATED_ERROR);
    }
}
