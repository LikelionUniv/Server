package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

import static likelion.univ.domain.user.exception.UserErrorCode.NOT_SUPER_ADMIN;

public class NotSuperAdminException extends BaseException {

    public NotSuperAdminException() {
        super(NOT_SUPER_ADMIN);
    }
}
