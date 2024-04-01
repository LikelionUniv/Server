package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;

public record PostEditData(
        Long postId,
        String title,
        String body,
        String thumbnail,
        MainCategory mainCategory,
        SubCategory subCategory
) {
    @QueryProjection
    public PostEditData(Long postId, String title, String body, String thumbnail, MainCategory mainCategory,
                        SubCategory subCategory) {
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.thumbnail = thumbnail;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
