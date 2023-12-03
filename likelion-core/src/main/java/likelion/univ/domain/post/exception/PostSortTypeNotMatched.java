package likelion.univ.domain.post.exception;

import likelion.univ.exception.base.BaseErrorCode;
import likelion.univ.exception.base.BaseException;

public class PostSortTypeNotMatched extends BaseException {

    public PostSortTypeNotMatched() {
        super(PostErrorCode.POST_SORT_TYPE_NOT_MATCHED);
    }
}
