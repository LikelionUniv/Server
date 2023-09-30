package likelion.univ.domain.postlike.exception;

import likelion.univ.exception.base.BaseException;

public class PostLikeNotFoundException extends BaseException {

    public PostLikeNotFoundException() {
        super(PostLikeErrorCode.LIKEPOST_NOT_FOUND);
    }
}
