package likelion.univ.domain.comment.exception;

import likelion.univ.exception.base.BaseException;

public class CreateCommentBadRequestException extends BaseException {
    public CreateCommentBadRequestException() {
        super(CommentErrorCode.CREATE_COMMENT_BAD_REQUEST);
    }
}
