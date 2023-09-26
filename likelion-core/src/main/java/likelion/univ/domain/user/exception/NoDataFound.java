package likelion.univ.domain.user.exception;

import likelion.univ.exception.base.BaseException;

public class NoDataFound extends BaseException {
    public NoDataFound(){
        super(UserErrorCode.NO_DATA_FOUND);
    }
}
