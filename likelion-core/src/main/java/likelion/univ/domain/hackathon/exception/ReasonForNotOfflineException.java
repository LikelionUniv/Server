package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.HACKATHON_FORM_NOT_EDITABLE;
import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.REASON_FOR_NOT_OFFLINE_NECESSARY;

import likelion.univ.exception.base.BaseException;

public class ReasonForNotOfflineException extends BaseException {

    public ReasonForNotOfflineException() {
        super(REASON_FOR_NOT_OFFLINE_NECESSARY);
    }
}
