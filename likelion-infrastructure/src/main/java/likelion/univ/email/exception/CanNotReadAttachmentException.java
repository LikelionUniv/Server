package likelion.univ.email.exception;

import likelion.univ.exception.base.BaseException;

public class CanNotReadAttachmentException extends BaseException {
    public CanNotReadAttachmentException() {
        super(EmailErrorCode.CAN_NOT_READ_ATTACHMENT);
    }
}
