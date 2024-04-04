package likelion.univ.domain.like.commentlike.exception;

import likelion.univ.exception.base.BaseException;

public class CommentLikeNotFoundException extends BaseException {

    public CommentLikeNotFoundException() {
        super(CommentLikeErrorCode.CREATE_LIKECOMMENT_BAD_REQUEST);
    }
}
