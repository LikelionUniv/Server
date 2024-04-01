package likelion.univ.comment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import lombok.Builder;

@Builder
public record CommentResponseDto(
        @Schema(description = "댓글 pk", example = "1")
        Long commentId,
        @Schema(description = "댓글 작성자 pk", example = "1")
        Long userId,
        @Schema(description = "댓글 작성자 이름", example = "김멋사")
        String userName,
        @Schema(description = "댓글 작성자 프로필 이미지 존재 여부", example = "true")
        Boolean hasUserProfileImageUrl,
        @Schema(description = "댓글 작성자 프로필 경로", example = "image url")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String userProfileImageUrl,
        @Schema(description = "로그인 유저가 댓글 작성자인지", example = "false")
        Boolean isLoginUserComment,
        @Schema(description = "게시글 작성자의 댓글인지", example = "true")
        Boolean isAuthorComment,
        @Schema(description = "로그인 유저가 해당 댓글을 좋아요 했는지", example = "false")
        Boolean isLikedByLoginUser,
        @Schema(description = "댓글에 대한 좋아요 개수", example = "27")
        Integer likeCount,
        @Schema(description = "댓글 내용", example = "멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사멋사")
        String body,
        @Schema(description = "지워진 댓글인지 여부", example = "false")
        Boolean isDeleted,
        @Schema(description = "댓글 생성 일자", example = "yyyy. MM. dd")
        String createdDate,
        @Schema(description = "대댓글이 있는지 여부", example = "true")
        Boolean hasChildComments,
        @Schema(description = "대댓글 목록")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<ChildCommentResponseDto> childComments
) {
    public static CommentResponseDto of(ParentCommentData parentComment, List<ChildCommentData> allChildComments,
                                        Long loginUserId, Long postAuthorId) {
        List<ChildCommentData> childCommentData = allChildComments.stream()
                .filter(i -> i.parentId().equals(parentComment.commentId())).toList();
        List<ChildCommentResponseDto> childComments = childCommentData.stream()
                .map(c -> ChildCommentResponseDto.of(c, loginUserId, postAuthorId)).toList();
        return CommentResponseDto.builder()
                .commentId(parentComment.commentId())
                .userId(parentComment.userId())
                .userName(parentComment.userName())
                .hasUserProfileImageUrl(parentComment.userProfileImageUrl() != null)
                .userProfileImageUrl(parentComment.userProfileImageUrl())
                .isLoginUserComment(parentComment.userId().equals(loginUserId))
                .isAuthorComment(parentComment.userId().equals(postAuthorId))
                .isLikedByLoginUser(parentComment.isLikedByLoginUser())
                .likeCount(parentComment.likeCount())
                .body(parentComment.body())
                .isDeleted(parentComment.isDeleted())
                .createdDate(parentComment.getFormattedDate())
                .hasChildComments(!childComments.isEmpty())
                .childComments(childComments)
                .build();
    }
    
}
