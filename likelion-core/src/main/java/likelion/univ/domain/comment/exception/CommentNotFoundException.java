package likelion.univ.domain.comment.exception;

import likelion.univ.exception.base.BaseException;

public class CommentNotFoundException extends BaseException {

    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
