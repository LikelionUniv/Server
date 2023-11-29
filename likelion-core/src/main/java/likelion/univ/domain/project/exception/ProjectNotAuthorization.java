package likelion.univ.domain.project.exception;

import likelion.univ.exception.base.BaseException;

public class ProjectNotAuthorization extends BaseException {
    public ProjectNotAuthorization() {
        super(ProjectErrorCode.PROJECT_NOT_AUTHORIZATION);
    }
}