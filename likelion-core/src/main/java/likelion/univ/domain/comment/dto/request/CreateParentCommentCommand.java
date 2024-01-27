package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record CreateParentCommentCommand(
        Long postId,
        Long loginUserId,
        String body
) {
}
