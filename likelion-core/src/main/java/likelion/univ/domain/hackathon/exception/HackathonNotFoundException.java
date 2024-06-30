package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.HACKATHON_NOT_FOUND;

import likelion.univ.exception.base.BaseException;

public class HackathonNotFoundException extends BaseException {
    public HackathonNotFoundException() {
        super(HACKATHON_NOT_FOUND);
    }
}
