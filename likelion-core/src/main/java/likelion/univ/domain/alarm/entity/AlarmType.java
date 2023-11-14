package likelion.univ.domain.alarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmType {
    NEW_UNIVERSITY_RECRUITING("신규 대학 모집 알람"),
    EXISTING_UNIVERSITY_RECRUITING("기존 대학 재신청 알람");

    private String value;
}
