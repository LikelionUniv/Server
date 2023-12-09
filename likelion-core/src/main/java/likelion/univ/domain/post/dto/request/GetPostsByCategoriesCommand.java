package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.enums.SubCategory;
import org.springframework.data.domain.Pageable;

public record GetPostsByCategoriesCommand(
        MainCategory mainCategory,
        SubCategory subCategory,
        Pageable pageable
) {
}
