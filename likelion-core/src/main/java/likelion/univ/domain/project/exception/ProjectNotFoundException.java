package likelion.univ.domain.project.exception;

import likelion.univ.exception.base.BaseException;

public class ProjectNotFoundException extends BaseException {
    public ProjectNotFoundException() {
        super(ProjectErrorCode.PROJECT_NOT_FOUND);
    }
}
