package likelion.univ.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentCreateChildRequestDto {
    private Long postId;
    private Long parentCommentId;
    private String body;
}
