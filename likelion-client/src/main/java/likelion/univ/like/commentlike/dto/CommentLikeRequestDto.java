package likelion.univ.like.commentlike.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import likelion.univ.domain.like.commentlike.dto.request.CommentLikeCommand;

public record CommentLikeRequestDto(
        @NotNull
        @Schema(description = "좋아요를 누르는 댓글 id")
        Long commentId
) {
    public CommentLikeCommand toCommand(Long userId) {
        return CommentLikeCommand.builder()
                .loginUserId(userId)
                .commentId(commentId)
                .build();
    }
}
