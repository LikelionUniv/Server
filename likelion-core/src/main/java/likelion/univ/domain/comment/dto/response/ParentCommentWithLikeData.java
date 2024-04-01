package likelion.univ.domain.comment.dto.response;

import java.time.LocalDateTime;
import likelion.univ.domain.comment.entity.Comment;
import lombok.Builder;

@Builder
public record ParentCommentWithLikeData(
        Long commentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Boolean isLikedByLoginUser,
        Integer likeCount,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {

    public static ParentCommentWithLikeData of(Comment parentComment, Boolean isLikedByLoginUser) {
        return ParentCommentWithLikeData.builder()
                .commentId(parentComment.getId())
                .userId(parentComment.getAuthor().getId())
                .userName(parentComment.getAuthor().getProfile().getName())
                .userProfileImageUrl(parentComment.getAuthor().getProfile().getProfileImage())
                .isLikedByLoginUser(isLikedByLoginUser)
                .likeCount(getLikeCount(parentComment))
                .body(parentComment.getBody())
                .isDeleted(parentComment.getIsDeleted())
                .createdDate(parentComment.getCreatedDate())
                .build();
    }

    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().size());
    }
}
