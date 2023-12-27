package likelion.univ.domain.comment.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ParentCommentData(
        Long commentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Integer likeCount,
        Boolean isLikedByLoginUser,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {
    @Builder
    @QueryProjection
    public ParentCommentData {}

    public static ParentCommentData of(Comment parentComment, Boolean isLikedByLoginUser) {
        return ParentCommentData.builder()
                .commentId(parentComment.getId())
                .userId(parentComment.getAuthor().getId())
                .userName(parentComment.getAuthor().getProfile().getName())
                .userProfileImageUrl(parentComment.getAuthor().getProfile().getProfileImage())
                .likeCount(getLikeCount(parentComment))
                .isLikedByLoginUser(isLikedByLoginUser)
                .body(parentComment.getBody())
                .isDeleted(parentComment.getIsDeleted())
                .createdDate(parentComment.getCreatedDate())
                .build();
    }

    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().size());
    }
}
