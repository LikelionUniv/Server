package likelion.univ.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class IncorrectClaimTokenException extends BaseException {
    public IncorrectClaimTokenException() {
        super(GlobalErrorCode.INCORRECT_CLAIM_TOKEN);
    }
}
