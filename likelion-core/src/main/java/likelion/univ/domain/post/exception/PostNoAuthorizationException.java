package likelion.univ.domain.post.exception;

import likelion.univ.exception.base.BaseException;

public class PostNoAuthorizationException extends BaseException {
    public PostNoAuthorizationException() {
        super(PostErrorCode.NOT_AUTHORIZATION);
    }
}
