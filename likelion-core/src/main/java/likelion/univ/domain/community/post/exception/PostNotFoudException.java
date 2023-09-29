package likelion.univ.domain.community.post.exception;

import likelion.univ.exception.base.BaseException;

public class PostNotFoudException extends BaseException {

    public PostNotFoudException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
