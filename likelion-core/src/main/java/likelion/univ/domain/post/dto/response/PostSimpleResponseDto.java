package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostSimpleResponseDto {
    private Long postId;

    private Long authorId; // 각 유저별 작성글 조회 기능 구현 목적

    private String authorName;

    private String title;

    private String body;

    private String thumbnail;

    private Integer likeCount;

    private MainCategory mainCategory;

    private SubCategory subCategory;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @QueryProjection
    public PostSimpleResponseDto(Long postId, Long authorId, String authorName, String title, String body, String thumbnail, Integer likeCount, MainCategory mainCategory, SubCategory subCategory, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.title = title;
        this.body = body;
        this.thumbnail = thumbnail;
        this.likeCount = likeCount;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}
