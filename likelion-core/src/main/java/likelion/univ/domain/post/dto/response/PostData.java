package likelion.univ.domain.post.dto.response;

import likelion.univ.common.processor.DateCustomFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record PostData(
        Long postId,
        Long authorId,
        String authorName,
        String authorProfileImageUrl,
        String title,
        String bodySummary,
        String thumbnailUrl,
        Long likeCount,
        Long commentCount,
        LocalDateTime createdDate
) {
    public static PostData getInstance(PostSimpleData post, Long likeCount, Long commentCount) {
        return new PostData(
                post.postId(),
                post.authorId(),
                post.authorName(),
                post.authorProfileImageUrl(),
                post.title(),
                post.body(),
                post.thumbnailUrl(),
                likeCount,
                commentCount,
                post.createdDate()
        );
    }
    public String getFormattedDate() {
        return DateCustomFormatter.format(this.createdDate);
    }

}
