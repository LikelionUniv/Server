package likelion.univ.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class NotMatchedTokenTypeException extends BaseException {

    public NotMatchedTokenTypeException() {
        super(GlobalErrorCode.NOT_MATCHED_TOKEN_TYPE);
    }
}
