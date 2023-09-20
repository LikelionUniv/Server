package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class IncorrectIssuerTokenException extends BaseException {
    public IncorrectIssuerTokenException() {
        super(SecurityErrorCode.INCORRECT_ISSUER_TOKEN);
    }
}
