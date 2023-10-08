package likelion.univ.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class PostDeleteRequestDto {
    @NotNull
    @Schema(description = "지우려는 게시글 id")
    private Long postId;
}
