package likelion.univ.domain.hackathon.exception;

import static likelion.univ.domain.hackathon.exception.HackathonErrorCode.CANNOT_MODIFY_OFFLINE_PARTICIPATION;

import likelion.univ.exception.base.BaseException;

public class CannotModifyOfflineParticipationException extends BaseException {

    public CannotModifyOfflineParticipationException() {
        super(CANNOT_MODIFY_OFFLINE_PARTICIPATION);
    }
}
