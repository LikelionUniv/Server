package likelion.univ.domain.comment.dto;

import likelion.univ.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDetailResponseDto {
    private Long commentId;
    private Long userId;
    private String username;
    private Long parentId;
    private String body;
    private Long likeCount;
    private Boolean isDeleted;

    public static CommentDetailResponseDto of(Comment comment) {
        return CommentDetailResponseDto.builder()
                .commentId(comment.getId())
                .userId(comment.getAuthor().getId())
                .username(comment.getAuthor().getProfile().getName())
                .parentId(comment.getParentComment().getId())
                .body(comment.getBody())
                .likeCount(getLikeCount(comment))
                .isDeleted(comment.getIsDeleted())
                .build();
    }
    private static long getLikeCount(Comment comment) {
        return comment.getCommentLikes().stream()
                .filter(l -> l.getIsCanceled().equals(false))
                .count();
    }
}
