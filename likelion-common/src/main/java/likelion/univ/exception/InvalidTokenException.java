package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(){
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
