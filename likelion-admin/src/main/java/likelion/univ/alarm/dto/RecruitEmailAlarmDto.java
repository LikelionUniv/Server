package likelion.univ.alarm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitEmailAlarmDto {

    @Schema(description = "받는 사람 (Email List)")
    private List<String> receivers;

    @Schema(description = "메일 제목")
    private String subject;

    @Schema(description = "본문 형식 [html / plain-text]")
    private String contentsType;

    @Schema(description = "메일 본문")
    private String contents;
}
