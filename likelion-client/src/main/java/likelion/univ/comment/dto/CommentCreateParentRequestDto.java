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
public class CommentCreateParentRequestDto {
    @NotNull
    @Schema(description = "댓글을 다는 게시글 id")
    private Long postId;
    @NotBlank
    @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
    private String body;
}
