package likelion.univ.alarm.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import likelion.univ.alarm.usecase.EmailRecruitAlarmUsecase;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AlarmContentsDto {
    private String subject;
    private String sender;
    private String universityName;
    private String content;
    private List<String> emails;
}
