package likelion.univ.domain.hackathon.exception;

import likelion.univ.exception.base.BaseException;

public class NoAuthorityGuestApplyHackathon extends BaseException {

    public NoAuthorityGuestApplyHackathon() {
        super(HackathonErrorCode.NO_AUTHORITY_GUEST_APPLY_HACKATHON);
    }
}
