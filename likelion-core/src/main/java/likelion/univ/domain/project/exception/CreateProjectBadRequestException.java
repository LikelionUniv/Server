package likelion.univ.domain.project.exception;

import likelion.univ.exception.base.BaseException;

public class CreateProjectBadRequestException extends BaseException {

    public CreateProjectBadRequestException() {
        super(ProjectErrorCode.CREATE_PROJECT_BAD_REQUEST);
    }
}
