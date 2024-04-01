package likelion.univ.domain.alarm.exception;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmErrorCode implements BaseErrorCode {

    EMAIL_ALREADY_REGISTERED_AS_ALARM(BAD_REQUEST, "ALARM_409", "해당 이메일은 이미 신청되어 있습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
