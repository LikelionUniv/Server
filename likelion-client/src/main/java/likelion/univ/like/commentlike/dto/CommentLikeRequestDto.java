package likelion.univ.like.commentlike.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeRequestDto {
    
    @NotNull
    @Schema(description = "좋아요를 누르는 댓글 id")
    private Long commentId;
}
