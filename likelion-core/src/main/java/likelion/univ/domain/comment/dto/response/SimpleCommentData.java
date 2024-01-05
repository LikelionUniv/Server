package likelion.univ.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SimpleCommentData {
    private Long commentId;
    private Long postId;

    public static SimpleCommentData of(Long commentId, Long postId) {
        return SimpleCommentData.builder()
                .commentId(commentId)
                .postId(postId)
                .build();
    }
}
