package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class EmailAlreadyRegistered extends BaseException {
    public EmailAlreadyRegistered() {
        super(UserErrorCode.EMAIL_ALREADY_REGISTERED);
    }
}
