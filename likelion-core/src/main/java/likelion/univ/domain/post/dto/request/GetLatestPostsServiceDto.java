package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import org.springframework.data.domain.Pageable;

public record GetLatestPostsServiceDto(
        MainCategory mainCategory,
        SubCategory subCategory,
        Pageable pageable
) {
}
