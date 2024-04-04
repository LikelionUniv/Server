package likelion.univ.domain.comment.dto.request;

import lombok.Builder;

@Builder
public record DeleteCommentCommand(
        Long commentId,
        Long loginUserId
) {

    public static DeleteCommentCommand of(Long commentId, Long loginUserId) {
        return new DeleteCommentCommand(commentId, loginUserId);
    }
}
