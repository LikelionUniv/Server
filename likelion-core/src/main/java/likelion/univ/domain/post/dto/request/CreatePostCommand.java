package likelion.univ.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record CreatePostCommand(
        String title,
        String body,
        Long authorId,
        String thumbnail,
        String mainCategory,
        String subCategory
) {

}
