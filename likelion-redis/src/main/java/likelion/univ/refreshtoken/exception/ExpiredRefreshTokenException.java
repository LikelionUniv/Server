package likelion.univ.refreshtoken.exception;

import likelion.univ.exception.base.BaseException;

public class ExpiredRefreshTokenException extends BaseException {
    
    public ExpiredRefreshTokenException() {
        super(RefreshTokenErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
