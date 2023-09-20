package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class AccessDeniedRequestException extends BaseException {

    public AccessDeniedRequestException() {
        super(GlobalErrorCode.ACCESS_DENIED_REQUEST);
    }
}
