package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class SortTypeNotMatchedException extends BaseException {
    
    public SortTypeNotMatchedException() {
        super(GlobalErrorCode.SORT_TYPE_NOT_MATCHED);
    }
}
