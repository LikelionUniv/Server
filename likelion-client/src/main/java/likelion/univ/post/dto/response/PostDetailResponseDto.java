package likelion.univ.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.dto.response.PostDetailData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.post.entity.PostCountInfo;

import java.util.List;

public record PostDetailResponseDto(
        @Schema(description = "게시글 pk", example = "1")
        Long postId,
        @Schema(description = "게시글 메인 카테고리", example = "FREE_BOARD")
        MainCategory mainCategory,
        @Schema(description = "게시글 서브 카테고리", example = "FREE_INFO")
        SubCategory subCategory,
        @Schema(description = "작성 유저 pk", example = "1")
        Long authorId,
        @Schema(description = "작성 유저 이름", example = "김멋사")
        String authorName,
        @Schema(description = "이미지 존재 여부", example = "true")
        Boolean hasAuthorProfileImageUrl,
        @Schema(description = "이미지가 존재할 경우, 이미지 url", example = "https://s3.~")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String authorProfileImageUrl,
        @Schema(description = "작성자 기수", example = "11")
        Long authorOrdinal,
        @Schema(description = "작성자 대학교", example = "멋쟁대학교")
        String universityName,
        @Schema(description = "로그인 유저가 게시글 작성자인지 여부", example = "false")
        Boolean isMyPost,
        @Schema(description = "로그인 유저가 게시글 작성자를 팔로우했는지 여부", example = "true")
        Boolean isFollowedAuthor,
        @Schema(description = "내가 좋아요 한 게시글인지 여부", example = "false")
        Boolean isLikedPost,
        @Schema(description = "게시글 좋아요 수", example = "11")
        Integer likeCount,
        @Schema(description = "게시글 댓글 수", example = "11")
        Integer commentCount,
        @Schema(description = "게시글 제목", example = "멋사 화이팅")
        String title,
        @Schema(description = "게시글 내용", example = "재밌어요 멋사")
        String body,
        @Schema(description = "게시글 작성일자", example = "2023. 6. 15")
        String createdDate
) {
    public PostDetailResponseDto(PostDetailData post, Integer likeCount, Integer commentCount, Long loginUserId) {
        this(
                post.postId(),
                post.mainCategory(),
                post.subCategory(),
                post.authorId(),
                post.authorName(),
                hasImageUrl(post.authorProfileImageUrl()),
                post.authorProfileImageUrl(),
                post.authorOrdinal(),
                post.universityName(),
                isMyPost(post.authorId(), loginUserId),
                post.isFollowedAuthor(),
                post.isLikedPost(),
                likeCount,
                commentCount,
                post.title(),
                post.body(),
                post.getFormattedDate()
        );
    }

    private static Boolean hasImageUrl(String imageUrl) {
        return imageUrl != null;
    }

    private static Boolean isMyPost(Long authorId, Long loginUserId) {
        return authorId.equals(loginUserId);
    }

    private static Integer commentCount(List<CommentResponseDto> comments) {
        Integer commentCount = comments.size();
        commentCount += comments.stream().mapToInt(i -> Math.toIntExact(i.childComments().size())).sum();
        return commentCount;
    }
    public static PostDetailResponseDto of(PostDetailData post, PostCountInfo postCountInfo, long loginUserId) {
        return new PostDetailResponseDto(post, Math.toIntExact(postCountInfo.getLikeCount()), Math.toIntExact(postCountInfo.getCommentCount()), loginUserId);
    }

}
