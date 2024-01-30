package likelion.univ.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record UpdatePostCommand(
        Long loginUserId,
        Long postId,
        String title,
        String thumbnail,
        String body,
        String mainCategory,
        String subCategory
) {
}
