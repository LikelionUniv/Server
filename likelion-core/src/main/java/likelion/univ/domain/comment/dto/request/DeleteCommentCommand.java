package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record DeleteCommentCommand(
    Long commentId,
    Long loginUserId
) {
    public static DeleteCommentCommand of(Long commentId, Long loginUserId) {
        return new DeleteCommentCommand(commentId, loginUserId);
    }
}
