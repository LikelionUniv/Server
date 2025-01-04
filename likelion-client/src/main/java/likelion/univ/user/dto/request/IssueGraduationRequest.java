package likelion.univ.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record IssueGraduationRequest(
        @NotNull(message = "기수를 입력해 주세요.")
        @Schema(description = "기수", example = "2", required = true)
        Long ordinal
) {
}