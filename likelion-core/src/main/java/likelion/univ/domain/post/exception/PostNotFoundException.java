package likelion.univ.domain.post.exception;

import likelion.univ.exception.base.BaseException;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
