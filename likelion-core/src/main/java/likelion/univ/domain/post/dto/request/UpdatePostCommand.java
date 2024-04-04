package likelion.univ.domain.post.dto.request;

import lombok.Builder;

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
