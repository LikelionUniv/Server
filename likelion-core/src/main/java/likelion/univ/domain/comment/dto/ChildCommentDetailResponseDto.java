package likelion.univ.domain.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

public record ChildCommentDetailResponseDto(
        Long commentId,
        Long parentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Boolean isLoginUserComment,
        Integer likeCount,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {
    @Builder
    @QueryProjection
    public ChildCommentDetailResponseDto(Long commentId, Long parentId, Long userId, String userName, String userProfileImageUrl, Boolean isLoginUserComment, Integer likeCount, String body, Boolean isDeleted, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.userId = userId;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isLoginUserComment = isLoginUserComment;
        this.likeCount = likeCount;
        this.body = body;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
    }

    public static ChildCommentDetailResponseDto of(Comment comment, User loginUser) {
        return ChildCommentDetailResponseDto.builder()
                .commentId(comment.getId())
                .parentId(comment.getParentComment().getId())
                .userId(comment.getAuthor().getId())
                .userName(comment.getAuthor().getProfile().getName())
                .userProfileImageUrl(comment.getAuthor().getProfile().getProfileImage())
                .isLoginUserComment(comment.getAuthor().equals(loginUser))
                .likeCount(getLikeCount(comment))
                .body(comment.getBody())
                .isDeleted(comment.getIsDeleted())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().stream()
                .filter(l -> l.getIsCanceled().equals(false))
                .count());
    }
}
