package likelion.univ.comment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import lombok.Builder;

@Builder
public record ChildCommentResponseDto(
        @Schema(description = "대댓글 pk", example = "1")
        Long commentId,
        @Schema(description = "부모댓글 pk", example = "1")
        Long parentId,
        @Schema(description = "대댓글 작성자 pk", example = "1")
        Long userId,
        @Schema(description = "대댓글 작성자 이름", example = "김멋사")
        String userName,
        @Schema(description = "대댓글 작성자 프로필 이미지 존재 여부", example = "true")
        Boolean hasUserProfileImage,
        @Schema(description = "대댓글 작성자 프로필 경로", example = "image url")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String userProfileImageUrl,
        @Schema(description = "로그인 유저가 대댓글 작성자인지", example = "false")
        Boolean isLoginUserComment,
        @Schema(description = "게시글 작성자의 대댓글인지", example = "true")
        Boolean isAuthorComment,
        @Schema(description = "댓글에 대하여 로그인 유저가 좋아요를 눌렀는지", example = "false")
        Boolean isLikedByLoginUser,
        @Schema(description = "대댓글에 대한 좋아요 개수", example = "27")
        Integer likeCount,
        @Schema(description = "대댓글 내용", example = "멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사")
        String body,
        @Schema(description = "지워진 대댓글인지 여부", example = "false")
        Boolean isDeleted,
        @Schema(description = "대댓글 생성 일자", example = "yyyy. MM. dd")
        String createdDate
) {
    public static ChildCommentResponseDto of(ChildCommentData childComment, Long loginUserId, Long postAuthorId) {
        return ChildCommentResponseDto.builder()
                .commentId(childComment.commentId())
                .parentId(childComment.parentId())
                .userId(childComment.userId())
                .userName(childComment.userName())
                .hasUserProfileImage(childComment.userProfileImageUrl() != null)
                .userProfileImageUrl(childComment.userProfileImageUrl())
                .isLoginUserComment(childComment.userId().equals(loginUserId))
                .isAuthorComment(childComment.userId().equals(postAuthorId))
                .isLikedByLoginUser(childComment.isLikedByLoginUser())
                .likeCount(childComment.likeCount())
                .body(childComment.body())
                .isDeleted(childComment.isDeleted())
                .createdDate(childComment.getFormattedDate())
                .build();
    }

}
