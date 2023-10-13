package likelion.univ.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentCreateChildServiceDto {
    private Long parentCommentId;
//    private Long postId;
    private Long loginUserId;
    private String body;
}
