package likelion.univ.alarm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitEmailAlarmDto {

    @Schema(description = "대학 이름(한글)")
    private String universityName;

    @Schema(description = "해당 기수(ex.2024년 -> 12)")
    private Integer generation;

    @Schema(description = "메일 제목")
    private String subject;

    @Schema(description = "본문 형식 [html / plain-text]")
    private String contentsType;

    @Schema(description = "메일 본문")
    private String contents;
}
