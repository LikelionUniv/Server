package likelion.univ.domain.hackathon.exception;

import static likelion.univ.constant.StaticValue.FORBIDDEN;
import static likelion.univ.constant.StaticValue.NOT_FOUND;
import static likelion.univ.domain.hackathon.entity.HackathonForm.HACKATHON_ORDINAL;

import likelion.univ.exception.base.BaseErrorCode;

public enum HackathonErrorCode implements BaseErrorCode {

    HACKATHON_FORM_NOT_FOUND(NOT_FOUND, "HACKATHON_404_1", "해커톤 신청서를 찾을 수 없습니다."),
    NO_AUTHORITY_GUEST_APPLY_HACKATHON(FORBIDDEN, "HACKATHON_403_1", "회원가입이 완료된 회원만 신청할 수 있습니다."),
    HACKATHON_FORM_NOT_EDITABLE(FORBIDDEN, "HACKATHON_403_2", "사용자가 작성한 해커톤 신청서가 아닙니다."),
    NO_AUTHORITY_ORDINAL_APPLY_HACKATHON(
            FORBIDDEN,
            "HACKATHON_403_2",
            "본 중앙 해커톤은 %d기 아기사자 및 운영진 참가 대상입니다".formatted(HACKATHON_ORDINAL)
    );

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
