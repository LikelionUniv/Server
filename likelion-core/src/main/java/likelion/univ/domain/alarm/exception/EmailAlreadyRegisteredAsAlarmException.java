package likelion.univ.domain.alarm.exception;

import likelion.univ.exception.base.BaseException;

public class EmailAlreadyRegisteredAsAlarmException extends BaseException {
    
    public EmailAlreadyRegisteredAsAlarmException() {
        super(AlarmErrorCode.EMAIL_ALREADY_REGISTERED_AS_ALARM);
    }
}
