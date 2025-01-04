package likelion.univ.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record IssueGraduationResponse(
        @Schema(description = "수료증 pdf url")
        String url
) {
    public static IssueGraduationResponse from(String url) {
        return IssueGraduationResponse.builder()
                .url(url)
                .build();
    }
}
