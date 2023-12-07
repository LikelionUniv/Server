package likelion.univ.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentIdData {
    private Long commentId;

    public static CommentIdData of(Long commentId) {
        return CommentIdData.builder()
                .commentId(commentId)
                .build();
    }
}
