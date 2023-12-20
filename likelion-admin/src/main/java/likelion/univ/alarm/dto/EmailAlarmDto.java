package likelion.univ.alarm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.user.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class EmailAlarmDto {

    @Schema(description = "알람 전송 대상 [USER(아기사자) / MANAGER(운영진) / UNIVERSITY_ADMIN(대학대표)]")
    private List<String> roles;

    @Schema(description = "메일 제목")
    private String subject;

    @Schema(description = "본문 형식 [html / plain-text]")
    private String contentsType;

    @Schema(description = "메일 본문")
    private String contents;
}
