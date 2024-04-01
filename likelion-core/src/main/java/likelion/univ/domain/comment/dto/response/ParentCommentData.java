package likelion.univ.domain.comment.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import likelion.univ.common.processor.DateCustomFormatter;
import likelion.univ.domain.comment.entity.Comment;
import lombok.Builder;

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
    public ParentCommentData {
    }

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

    public String getFormattedDate() {
        return DateCustomFormatter.format(this.createdDate);
    }
}
