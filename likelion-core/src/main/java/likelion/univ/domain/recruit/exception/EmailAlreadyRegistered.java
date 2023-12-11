package likelion.univ.domain.recruit.exception;

import likelion.univ.exception.base.BaseException;

public class EmailAlreadyRegistered extends BaseException {
    public EmailAlreadyRegistered() {
        super(RecruitErrorCode.EMAIL_ALREADY_REGISTERED);
    }
}
