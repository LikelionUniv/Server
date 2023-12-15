package likelion.univ.domain.recruit.exception;

import likelion.univ.exception.base.BaseException;

public class RecruitNotFound extends BaseException {

    public RecruitNotFound() {
        super(RecruitErrorCode.RECRUIT_NOT_FOUND);
    }
}
