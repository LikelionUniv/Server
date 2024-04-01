package likelion.univ.domain.post.dto.request;


import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.enums.SubCategory;


public record GetPostsByCategorySearchCommand(
        PostOrderCondition orderCondition,
        String searchTitle,
        MainCategory mainCategory,
        SubCategory subCategory
) {

    public GetPostsByCategorySearchCommand(PostOrderCondition orderCondition, String searchTitle, String mainCategory,
                                           String subCategory) {
        this(orderCondition, searchTitle, MainCategory.findByTitle(mainCategory), SubCategory.findByTitle(subCategory));
    }
}
