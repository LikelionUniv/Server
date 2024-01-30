package likelion.univ.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.post.entity.PostCountInfo;

public record PostResponseDto(
        @Schema(description = "게시글 pk", example = "1")
        Long postId,
        @Schema(description = "게시글 메인 카테고리", example = "자유게시판")
        String mainCategory,
        @Schema(description = "게시글 서브 카테고리", example = "정보공유")
        String subCategory,
        @Schema(description = "작성 유저 pk", example = "1")
        Long authorId,
        @Schema(description = "작성 유저 이름", example = "김멋사")
        String authorName,
        @Schema(description = "프로필 이미지 존재 여부", example = "true")
        Boolean hasAuthorProfileImage,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "프로필 이미지가 존재할 경우, 이미지 url", example = "https://s3.~")
        String authorProfileImageUrl,
        @Schema(description = "게시글 제목", example = "멋사 화이팅")
        String title,
        @Schema(description = "게시글 내용 요약", example = "재밌어요 멋사")
        String bodySummary,
        @Schema(description = "썸네일 이미지 존재 여부", example = "true")
        Boolean hasThumbnailUrl,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "썸네일 이미지가 존재할 경우, 이미지 url", example = "https://s3.~")
        String thumbnailUrl,
        @Schema(description = "게시글 좋아요 수", example = "11")
        Long likeCount,
        @Schema(description = "게시글 댓글 수", example = "11")
        Long commentCount,
        @Schema(description = "게시글 작성일자", example = "2023. 6. 15")
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
                post.getParsedBody(),
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
