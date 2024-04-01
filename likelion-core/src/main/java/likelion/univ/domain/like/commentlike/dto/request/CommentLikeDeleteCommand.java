package likelion.univ.domain.like.commentlike.dto.request;

import lombok.Builder;

@Builder
public record CommentLikeDeleteCommand(
        Long commentId,
        Long loginUserId
) {
}
