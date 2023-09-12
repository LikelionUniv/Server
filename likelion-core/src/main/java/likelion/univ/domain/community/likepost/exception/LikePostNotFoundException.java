package likelion.univ.domain.community.likepost.exception;

import likelion.univ.exception.base.BaseException;

public class LikePostNotFoundException  extends BaseException {

    public LikePostNotFoundException() {
        super(LikePostErrorCode.LIKEPOST_NOT_FOUND);
    }
}
