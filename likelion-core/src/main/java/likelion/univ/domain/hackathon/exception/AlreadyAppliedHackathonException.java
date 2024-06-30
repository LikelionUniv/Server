package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.ALREADY_APPLIED_HACKATHON;

import likelion.univ.exception.base.BaseException;

public class AlreadyAppliedHackathonException extends BaseException {

    public AlreadyAppliedHackathonException() {
        super(ALREADY_APPLIED_HACKATHON);
    }
}
