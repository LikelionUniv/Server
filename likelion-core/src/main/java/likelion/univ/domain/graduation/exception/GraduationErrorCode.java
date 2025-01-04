package likelion.univ.domain.graduation.exception;

import likelion.univ.exception.base.BaseErrorCode;

import static likelion.univ.constant.StaticValue.NOT_FOUND;

public enum GraduationErrorCode implements BaseErrorCode {
    GRADUATION_NOT_FOUND(NOT_FOUND, "GRADUATION_404_1", "수료 정보를 찾을 수 없습니다."),
    ;

    private final int httpStatus;
    private final String code;
    private final String message;

    GraduationErrorCode(int httpStatus, String code, String message) {
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