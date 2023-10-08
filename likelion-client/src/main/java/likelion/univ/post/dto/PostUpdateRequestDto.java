package likelion.univ.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class PostUpdateRequestDto {
    @NotBlank
    @Schema(description = "게시글 제목 수정", example = "수정된 제목입니다.")
    private String title;
    @NotBlank
    @Schema(description = "게시글 내용 수정", example = "수정된 내용입니다.")
    private String body;
    @Schema(description = "게시글 썸네일 수정", example = "수정된 썸네일입니다.")
    private String thumbnail;
}
