package likelion.univ.domain.post.dto.request;

import lombok.Builder;

@Builder
public record DeletePostCommand(
        Long postId,
        Long loginUserId
) {
}
