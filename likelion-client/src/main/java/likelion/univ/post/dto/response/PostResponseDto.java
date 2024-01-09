package likelion.univ.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.post.entity.PostCountInfo;

public record PostResponseDto(
        Long postId,
        String mainCategory,
        String subCategory,
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
        String createdDate
) {
    private PostResponseDto(PostSimpleData post, Long likeCount, Long commentCount) {
        this(
                post.postId(),
                post.mainCategory().getTitle(),
                post.subCategory().getTitle(),
                post.authorId(),
                post.authorName(),
                post.authorProfileImageUrl() != null,
                post.authorProfileImageUrl(),
                post.title(),
                post.body(),
                post.thumbnailUrl() != null,
                post.thumbnailUrl(),
                likeCount,
                commentCount,
                post.getFormattedDate()
        );

    }

    public static PostResponseDto of(PostSimpleData post, PostCountInfo postCountInfo) {
        return new PostResponseDto(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount());
    }

}
