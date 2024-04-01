package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;

public record GetPostsByCategoriesCommand(
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
