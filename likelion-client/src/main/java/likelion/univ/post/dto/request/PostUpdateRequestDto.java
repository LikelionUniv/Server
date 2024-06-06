package likelion.univ.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import likelion.univ.domain.post.dto.request.UpdatePostCommand;
import lombok.Builder;

@Builder
public record PostUpdateRequestDto(
        @NotBlank
        @Schema(description = "게시글 제목 수정", example = "수정된 제목입니다.")
        String title,
        @NotBlank
        @Schema(description = "게시글 내용 수정", example = "수정된 내용입니다.")
        String body,
        @Schema(description = "게시글 썸네일 수정", example = "수정된 썸네일입니다.")
        String thumbnail,
        @NotBlank
        @Schema(description = "게시글 메인 카테고리 수정", example = "멋대 중앙")
        String mainCategory,
        @NotBlank
        @Schema(description = "게시글 서브 카테고리 수정", example = "정보공유")
        String subCategory
) {
    public UpdatePostCommand toCommand(Long postId, Long userId) {
        return UpdatePostCommand.builder()
                .loginUserId(userId)
                .postId(postId)
                .title(title)
                .thumbnail(thumbnail)
                .body(body)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .build();
    }
}
