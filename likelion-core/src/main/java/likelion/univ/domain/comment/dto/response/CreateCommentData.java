package likelion.univ.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateCommentData {
    private Long commentId;
    private Long postId;

    public static CreateCommentData of(Long commentId, Long postId) {
        return CreateCommentData.builder()
                .commentId(commentId)
                .postId(postId)
                .build();
    }
}
