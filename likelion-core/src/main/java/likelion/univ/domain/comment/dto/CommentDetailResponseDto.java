package likelion.univ.domain.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDetailResponseDto {
    private Long commentId;
    private Long userId;
    private String username;
    private Long parentId;
    private String body;
    private Integer likeCount;
    private Boolean isDeleted;

    @QueryProjection
    public CommentDetailResponseDto(Long commentId, Long userId, String username, Long parentId, String body, Integer likeCount, Boolean isDeleted) {
        this.commentId = commentId;
        this.userId = userId;
        this.username = username;
        this.parentId = parentId;
        this.body = body;
        this.likeCount = likeCount;
        this.isDeleted = isDeleted;
    }

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
    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().stream()
                .filter(l -> l.getIsCanceled().equals(false))
                .count());
    }
}
