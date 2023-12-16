package likelion.univ.like.postlike.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public record PostLikeRequestDto(
        @NotNull
        @Schema(description = "좋아요를 누르는 게시글 id")
        Long postId
) {

}
