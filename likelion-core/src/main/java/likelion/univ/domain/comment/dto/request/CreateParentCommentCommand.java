package likelion.univ.domain.comment.dto.request;

import lombok.Builder;

@Builder
public record CreateParentCommentCommand(
        Long postId,
        Long loginUserId,
        String body
) {
}
