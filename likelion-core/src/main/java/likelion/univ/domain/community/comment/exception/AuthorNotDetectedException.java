package likelion.univ.domain.community.comment.exception;

import likelion.univ.exception.base.BaseException;

public class AuthorNotDetectedException extends BaseException {
    public AuthorNotDetectedException() {
        super(CommentErrorCode.AUTHOR_NOT_DETECTED);
    }
}
