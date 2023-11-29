package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class NotAdminForbiddenException extends BaseException {
    public NotAdminForbiddenException() { super(UserErrorCode.NOT_ADMIN_FORBIDDEN);}
}