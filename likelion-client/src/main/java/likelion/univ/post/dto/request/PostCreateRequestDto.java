package likelion.univ.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotBlank
    @Schema(description = "제목", example = "LIKELIONUNIV 프로젝트 회고", required = true)
    private String title;
    @NotBlank
    @Schema(description = "내용", example = "프로젝트 재밌었다.", required = true)
    private String body;
    @Schema(description = "썸네일 이미지", example = "string")
    private String thumbnail;
    @NotNull
    @Schema(description = "메인 카테고리", example = "자유게시판", required = true)
    private String mainCategory;
    @NotNull
    @Schema(description = "서브 카테고리", example = "정보공유", required = true)
    private String subCategory;
}
