package likelion.univ.domain.graduation.exception;

import likelion.univ.exception.base.BaseException;

import static likelion.univ.domain.graduation.exception.GraduationErrorCode.GRADUATION_NOT_FOUND;

public class GraduationNotFoundException extends BaseException {

    public GraduationNotFoundException() {
        super(GRADUATION_NOT_FOUND);
    }
}
