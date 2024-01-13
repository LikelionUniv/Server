package likelion.univ.domain.post.dto.request;


import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.enums.SubCategory;
import org.springframework.data.domain.Pageable;


public record GetPostsByCategorySearchCommand(
        PostOrderCondition orderCondition,
        String searchTitle,
        MainCategory mainCategory,
        SubCategory subCategory,
        Pageable pageable
) {
    public GetPostsByCategorySearchCommand(PostOrderCondition orderCondition, String searchTitle, String mainCategory, String subCategory, Pageable pageable) {
        this(orderCondition, searchTitle, MainCategory.findByTitle(mainCategory), SubCategory.findByTitle(subCategory), pageable);
    }
}
