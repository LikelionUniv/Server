package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class InvalidSignatureTokenException extends BaseException {
    public InvalidSignatureTokenException(){
        super(GlobalErrorCode.INVALID_SIGNATURE_TOKEN);
    }
}
