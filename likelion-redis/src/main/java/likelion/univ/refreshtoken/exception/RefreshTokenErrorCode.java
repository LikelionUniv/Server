package likelion.univ.refreshtoken.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum RefreshTokenErrorCode implements BaseErrorCode {
    EXPIRED_REFRESH_TOKEN(UNAUTHORIZED, "REFRESH_TOKEN_401", "해당 리프레쉬토큰은 만료되었습니다.");


    private final int httpStatus;
    private final String code;
    private final String message;
}
