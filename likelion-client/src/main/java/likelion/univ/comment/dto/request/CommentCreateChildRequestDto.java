package likelion.univ.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateChildRequestDto {
    @NotBlank
    @Schema(description = "대댓글 내용", example = "대댓글 내용입니다.")
    private String body;
}
