package likelion.univ.domain.post.exception;

import likelion.univ.exception.base.BaseException;
import lombok.Getter;

public class NoSuchCategoryException extends BaseException {
    public NoSuchCategoryException() {
        super(PostErrorCode.CATEGORY_NOT_FOUND);
    }
}

