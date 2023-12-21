package likelion.univ.domain.comment.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

public record ChildCommentData(
        Long commentId,
        Long parentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Integer likeCount,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {
    @Builder
    @QueryProjection
    public ChildCommentData(Long commentId, Long parentId, Long userId, String userName, String userProfileImageUrl, Integer likeCount, String body, Boolean isDeleted, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.userId = userId;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.likeCount = likeCount;
        this.body = body;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
    }

    public static ChildCommentData of(Comment comment, Boolean isLikedByLoginUser) {
        return ChildCommentData.builder()
                .commentId(comment.getId())
                .parentId(comment.getParentComment().getId())
                .userId(comment.getAuthor().getId())
                .userName(comment.getAuthor().getProfile().getName())
                .userProfileImageUrl(comment.getAuthor().getProfile().getProfileImage())
                .likeCount(getLikeCount(comment))
                .body(comment.getBody())
                .isDeleted(comment.getIsDeleted())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().size());
    }
}
