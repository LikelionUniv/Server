package likelion.univ.email.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class InvalidAttachment extends BaseException {
    public InvalidAttachment() {
        super(EmailErrorCode.INVALID_ATTACHMENT);
    }
}
