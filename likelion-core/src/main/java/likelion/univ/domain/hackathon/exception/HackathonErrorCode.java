package likelion.univ.domain.hackathon.exception;

import static likelion.univ.constant.StaticValue.FORBIDDEN;

import likelion.univ.exception.base.BaseErrorCode;

public enum HackathonErrorCode implements BaseErrorCode {

    NO_AUTHORITY_APPLY_HACKATHON(FORBIDDEN, "HACKATHON_403_1", "회원가입이 완료된 회원만 신청할 수 있습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;

    HackathonErrorCode(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getHttpStatus() {
        return this.httpStatus;
    }
}
