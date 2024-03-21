package likelion.univ.example.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;

public record CreateExampleRequestDto(
        @NotBlank
        @Schema(description = "댓글 내용", example = "안녕하세요. 멋쟁이사자처럼입니다.", required = true)
        String body
) {
}
