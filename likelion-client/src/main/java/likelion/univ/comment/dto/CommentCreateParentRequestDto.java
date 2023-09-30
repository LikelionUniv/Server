package likelion.univ.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentCreateParentRequestDto {
    private Long postId;
    private String body;
}
