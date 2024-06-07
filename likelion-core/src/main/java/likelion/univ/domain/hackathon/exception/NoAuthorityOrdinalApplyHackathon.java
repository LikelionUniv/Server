package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.NO_AUTHORITY_ORDINAL_APPLY_HACKATHON;

import likelion.univ.exception.base.BaseException;

public class NoAuthorityOrdinalApplyHackathon extends BaseException {

    public NoAuthorityOrdinalApplyHackathon() {
        super(NO_AUTHORITY_ORDINAL_APPLY_HACKATHON);
    }
}
