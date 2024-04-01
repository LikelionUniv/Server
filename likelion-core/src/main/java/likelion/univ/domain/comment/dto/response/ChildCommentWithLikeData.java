package likelion.univ.domain.comment.dto.response;

import java.time.LocalDateTime;
import likelion.univ.domain.comment.entity.Comment;
import lombok.Builder;

@Builder
public record ChildCommentWithLikeData(
        Long commentId,
        Long parentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Boolean isLikedByLoginUser,
        Integer likeCount,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {
    public static ChildCommentWithLikeData of(Comment comment, Boolean isLikedByLoginUser) {
        return ChildCommentWithLikeData.builder()
                .commentId(comment.getId())
                .parentId(comment.getParentComment().getId())
                .userId(comment.getAuthor().getId())
                .userName(comment.getAuthor().getProfile().getName())
                .userProfileImageUrl(comment.getAuthor().getProfile().getProfileImage())
                .isLikedByLoginUser(isLikedByLoginUser)
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

