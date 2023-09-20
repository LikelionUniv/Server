package likelion.univ.recruit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitAlarmContentDto {
    private String subject;
    private String sender;
    private String contents;
}
