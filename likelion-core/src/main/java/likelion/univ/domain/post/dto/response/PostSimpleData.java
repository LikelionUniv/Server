package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostSimpleData(
    Long postId,
    Long authorId,
    String authorName,
    String authorProfileImageUrl,
    String title,
    String bodySummary,
    String thumbnailUrl,
    LocalDateTime createdDate

) {
    @QueryProjection
    public PostSimpleData(Long postId, Long authorId, String authorName, String authorProfileImageUrl, String title, String bodySummary, String thumbnailUrl, LocalDateTime createdDate) {
        if (bodySummary.length() > 300) {
            bodySummary = bodySummary.substring(0, 300);
        }

        this.postId = postId;
        this.authorId = authorId;
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
                post.getAuthor().getProfile().getName(),
                post.getAuthor().getProfile().getProfileImage(),
                post.getTitle(),
                body,
                post.getThumbnail(),
                post.getCreatedDate()
        );
    }
}
