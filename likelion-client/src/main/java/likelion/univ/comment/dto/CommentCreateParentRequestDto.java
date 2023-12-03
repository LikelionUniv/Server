package likelion.univ.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateParentRequestDto {
    @NotBlank
    @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
    private String body;
}
