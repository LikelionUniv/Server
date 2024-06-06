package likelion.univ.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.dto.request.CreatePostCommand;

public record PostCreateRequestDto(
        @NotBlank
        @Schema(description = "제목", example = "LIKELIONUNIV 프로젝트 회고", required = true)
        String title,

        @NotBlank
        @Schema(description = "내용", example = "프로젝트 재밌었다.", required = true)
        String body,

        @Schema(description = "썸네일 이미지", example = "string")
        String thumbnail,

        @NotNull
        @Schema(description = "메인 카테고리", example = "자유게시판", required = true)
        String mainCategory,

        @NotNull
        @Schema(description = "서브 카테고리", example = "정보공유", required = true)
        String subCategory
) {
    public CreatePostCommand toCommand(Long userId) {
        return CreatePostCommand.builder()
                .title(title)
                .body(body)
                .authorId(userId)
                .thumbnail(thumbnail)
                .mainCategory(MainCategory.findByTitle(mainCategory))
                .subCategory(SubCategory.findByTitle(subCategory))
                .build();
    }
}
