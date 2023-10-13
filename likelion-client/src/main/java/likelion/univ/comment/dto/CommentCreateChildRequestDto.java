package likelion.univ.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CommentCreateChildRequestDto {
    @NotNull
    @Schema(description = "대댓글을 다는 댓글 id")
    private Long parentCommentId;
    @NotBlank
    @Schema(description = "대댓글 내용", example = "대댓글 내용입니다.")
    private String body;
}
