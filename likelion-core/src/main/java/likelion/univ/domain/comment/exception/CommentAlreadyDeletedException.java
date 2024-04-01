package likelion.univ.domain.comment.exception;

import likelion.univ.exception.base.BaseException;

public class CommentAlreadyDeletedException extends BaseException {

    public CommentAlreadyDeletedException() {
        super(CommentErrorCode.COMMENT_ALREADY_DELETED);
    }
}
