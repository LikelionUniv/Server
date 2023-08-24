package likelion.univ.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class PublicKeyNotFoundException extends BaseException {

    public PublicKeyNotFoundException() {
        super(GlobalErrorCode.PUBLIC_KEY_NOT_FOUND_TOKEN);
    }
}
