package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public record PostSimpleResponseDto(
    Long postId,
    Long authorId, // 각 유저별 작성글 조회 기능 구현 목적
    String authorName,
    String authorProfileUrl,
    String title,
    String bodySummary,
    String thumbnailUrl,
    Integer likeCount,
    Integer commentCount,
    MainCategory mainCategory,
    SubCategory subCategory,
    LocalDateTime createdDate

) {
    @QueryProjection
    public PostSimpleResponseDto(Long postId, Long authorId, String authorName,String authorProfileUrl, String title, String bodySummary, String thumbnailUrl, Integer likeCount, Integer commentCount, MainCategory mainCategory, SubCategory subCategory, LocalDateTime createdDate) {
        if (bodySummary.length() > 300) {
            bodySummary = bodySummary.substring(0, 300);
        }

        this.postId = postId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorProfileUrl = authorProfileUrl;
        this.title = title;
        this.bodySummary = bodySummary;
        this.thumbnailUrl = thumbnailUrl;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.createdDate = createdDate;
    }
}
