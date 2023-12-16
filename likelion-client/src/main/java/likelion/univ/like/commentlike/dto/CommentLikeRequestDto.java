package likelion.univ.like.commentlike.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeRequestDto {
    @NotNull
    @Schema(description = "좋아요를 누르는 댓글 id")
    private Long commentId;
}
