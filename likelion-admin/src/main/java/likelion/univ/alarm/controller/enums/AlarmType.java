package likelion.univ.alarm.controller.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum AlarmType {
    EMAIL("emailAlarmUsecase"),
    SMS("smsAlarmUsecase"),
    KAKAO("kakaoAlarmUsecase"),

    RECRUIT_EMAIL("emailRecruitAlarmUsecase"),
    RECRUIT_SMS("smsRecruitAlarmUsecase"),
    RECRUIT_KAKAO("kakaoRecruitAlarmUsecase"),;


    private final String usecaseName;

    public static String of(String type) {
        if (Arrays.stream(values()).count() == 0 ) {
            throw new IllegalStateException("일치하는 타입이 없음");
        }
        return valueOf(type.toUpperCase()).usecaseName;
    }
}
