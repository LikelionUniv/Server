package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class ForbiddenAuthorization extends BaseException {
    public ForbiddenAuthorization(){
        super(UserErrorCode.FORBIDDEN_AUTHORIZATION);
    }
}
