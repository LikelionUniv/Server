package likelion.univ.domain.hackathon.exception;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;
import static likelion.univ.constant.StaticValue.FORBIDDEN;
import static likelion.univ.constant.StaticValue.NOT_FOUND;
import static likelion.univ.domain.hackathon.entity.HackathonForm.HACKATHON_ORDINAL;

import likelion.univ.exception.base.BaseErrorCode;

public enum HackathonErrorCode implements BaseErrorCode {
    REASON_FOR_NOT_OFFLINE_NECESSARY(BAD_REQUEST, "HACKATHON_400_1", "오프라인 해커톤에 불참하는 경우, 불참 사유 작성은 필수입니다."),
    ALREADY_APPLIED_HACKATHON(BAD_REQUEST, "HACKATHON_400_2", "이미 신청한 해커톤입니다."),
    CANNOT_MODIFY_OFFLINE_PARTICIPATION(BAD_REQUEST, "HACKATHON_400_3", "해커톤 오프라인 참석 여부를 변경할 수 없습니다."),
    HACKATHON_NOT_FOUND(NOT_FOUND, "HACKATHON_404_1", "해커톤을 찾을 수 없습니다."),
    HACKATHON_FORM_NOT_FOUND(NOT_FOUND, "HACKATHON_404_2", "해커톤 신청서를 찾을 수 없습니다."),
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
