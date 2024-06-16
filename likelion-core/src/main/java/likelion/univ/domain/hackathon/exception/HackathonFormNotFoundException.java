package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.HACKATHON_FORM_NOT_FOUND;

import likelion.univ.exception.base.BaseException;

public class HackathonFormNotFoundException extends BaseException {

    public HackathonFormNotFoundException() {
        super(HACKATHON_FORM_NOT_FOUND);
    }
}
