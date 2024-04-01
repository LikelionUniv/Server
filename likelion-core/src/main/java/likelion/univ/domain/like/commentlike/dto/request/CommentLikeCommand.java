package likelion.univ.domain.like.commentlike.dto.request;

import lombok.Builder;

@Builder
public record CommentLikeCommand(
        Long loginUserId,
        Long commentId
) {
}
