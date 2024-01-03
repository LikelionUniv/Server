package likelion.univ.domain.post.dto.request;


import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import org.springframework.data.domain.Pageable;


public record GetPostsByCategorySearchCommand(
        String searchTitle,
        MainCategory mainCategory,
        SubCategory subCategory,
        Pageable pageable
) {
    public GetPostsByCategorySearchCommand(String searchTitle, String mainCategory, String subCategory, Pageable pageable) {
        this(searchTitle, MainCategory.findByTitle(mainCategory), SubCategory.findByTitle(subCategory), pageable);
    }
}
