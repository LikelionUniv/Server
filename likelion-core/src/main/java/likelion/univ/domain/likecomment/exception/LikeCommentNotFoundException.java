package likelion.univ.domain.likecomment.exception;

import likelion.univ.exception.base.BaseException;

public class LikeCommentNotFoundException extends BaseException {
    public LikeCommentNotFoundException() {
        super(LikeCommentErrorCode.CREATE_LIKECOMMENT_BAD_REQUEST);
    }
}
