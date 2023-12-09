package likelion.univ.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.univ.domain.post.dto.response.PostData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.post.entity.PostCountInfo;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long postId,
        Long authorId,
        String authorName,
        Boolean hasAuthorProfileImage,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String authorProfileImageUrl,
        String title,
        String bodySummary,
        Boolean hasThumbnailUrl,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String thumbnailUrl,
        Long likeCount,
        Long commentCount,
        LocalDateTime createdDate
) {
    private PostResponseDto(PostSimpleData post, Long likeCount, Long commentCount) {
        this(
                post.postId(),
                post.authorId(),
                post.authorName(),
                post.authorProfileImageUrl() != null,
                post.authorProfileImageUrl(),
                post.title(),
                post.bodySummary(),
                post.thumbnailUrl() != null,
                post.thumbnailUrl(),
                likeCount,
                commentCount,
                post.createdDate()
        );
    }

    public static PostResponseDto of(PostSimpleData post, PostCountInfo postCountInfo) {
        return new PostResponseDto(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount());
    }
}
