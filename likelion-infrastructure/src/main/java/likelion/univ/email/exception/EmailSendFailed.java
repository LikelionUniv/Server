package likelion.univ.email.exception;

import likelion.univ.exception.base.BaseException;

public class EmailSendFailed extends BaseException {
    public EmailSendFailed() {
        super(EmailErrorCode.EMAIL_SEND_FAILED);
    }
}
