package likelion.univ.like.postlike.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;

public record PostLikeRequestDto(
        @NotNull
        @Schema(description = "좋아요를 누르는 게시글 id")
        Long postId
) {
    public PostLikeCommand toCommand(Long userId) {
        return new PostLikeCommand(postId, userId);
    }
}
