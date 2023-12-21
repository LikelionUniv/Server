package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.entity.Post;
import lombok.Builder;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Builder
public record PostSimpleData(
    Long postId,
    Long authorId,
    MainCategory mainCategory,
    SubCategory subCategory,
    String authorName,
    String authorProfileImageUrl,
    String title,
    String bodySummary,
    String thumbnailUrl,
    LocalDateTime createdDate

) {
    @QueryProjection
    public PostSimpleData(Long postId, Long authorId, MainCategory mainCategory, SubCategory subCategory, String authorName, String authorProfileImageUrl, String title, String bodySummary, String thumbnailUrl, LocalDateTime createdDate) {
        if (bodySummary.length() > 300) {
            bodySummary = bodySummary.substring(0, 300);
        }

        this.postId = postId;
        this.authorId = authorId;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.authorName = authorName;
        this.authorProfileImageUrl = authorProfileImageUrl;
        this.title = title;
        this.bodySummary = bodySummary;
        this.thumbnailUrl = thumbnailUrl;
        this.createdDate = createdDate;
    }

    public static PostSimpleData of(Post post) {
        String body = post.getBody();
        if (body.length() > 300) {
            body = body.substring(0, 300);
        }
        return new PostSimpleData(
                post.getId(),
                post.getAuthor().getId(),
                post.getMainCategory(),
                post.getSubCategory(),
                post.getAuthor().getProfile().getName(),
                post.getAuthor().getProfile().getProfileImage(),
                post.getTitle(),
                body,
                post.getThumbnail(),
                post.getCreatedDate()
        );
    }

    public String getFormattedDate() {
        String createdDate = this.createdDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        int dateLength = createdDate.length();
        return createdDate.substring(0, dateLength - 1);
    }

}
