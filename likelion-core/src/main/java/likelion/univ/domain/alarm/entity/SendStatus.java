package likelion.univ.domain.alarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SendStatus {

    NOT_SENT("NOT_SENT"),
    RESERVED("RESERVED"),
    SENT("SENT");

    private String value;
}
