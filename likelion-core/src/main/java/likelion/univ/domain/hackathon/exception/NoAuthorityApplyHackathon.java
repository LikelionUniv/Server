package likelion.univ.domain.hackathon.exception;

import likelion.univ.exception.base.BaseException;

public class NoAuthorityApplyHackathon extends BaseException {

    public NoAuthorityApplyHackathon() {
        super(HackathonErrorCode.NO_AUTHORITY_APPLY_HACKATHON);
    }
}
