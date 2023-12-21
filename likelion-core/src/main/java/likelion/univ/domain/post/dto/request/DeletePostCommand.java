package likelion.univ.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record DeletePostCommand(
        Long postId,
        Long loginUserId
) {
}
