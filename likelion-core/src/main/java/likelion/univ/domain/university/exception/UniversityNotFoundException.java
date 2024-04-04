package likelion.univ.domain.university.exception;

import likelion.univ.exception.base.BaseException;

public class UniversityNotFoundException extends BaseException {

    public UniversityNotFoundException() {
        super(UniversityErrorCode.UNIVERSITY_NOT_FOUND);
    }
}
