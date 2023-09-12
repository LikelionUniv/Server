package likelion.univ.domain.community.post.exception;

import likelion.univ.exception.base.BaseException;
import likelion.univ.domain.community.post.exception.PostErrorCode.*;
public class PostNotFoudException extends BaseException {

    public PostNotFoudException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
