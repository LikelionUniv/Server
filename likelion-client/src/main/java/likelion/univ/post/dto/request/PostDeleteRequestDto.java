package likelion.univ.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostDeleteRequestDto {

    @NotNull
    @Schema(description = "지우려는 게시글 id")
    private Long postId;
}
