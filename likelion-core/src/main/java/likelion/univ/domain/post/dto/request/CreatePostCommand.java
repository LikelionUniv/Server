package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import lombok.Builder;

@Builder
public record CreatePostCommand(
        String title,
        String body,
        Long authorId,
        String thumbnail,
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
