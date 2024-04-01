package likelion.univ.domain.comment.exception;

import likelion.univ.exception.base.BaseException;

public class NotAuthorizedException extends BaseException {

    public NotAuthorizedException() {
        super(CommentErrorCode.AUTHOR_NOT_DETECTED);
    }
}
