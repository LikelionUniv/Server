package likelion.univ.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.post.dto.response.PostEditData;

public record PostEditResponseDto(
        @Schema(description = "게시글 pk", example = "1")
        Long postId,
        @Schema(description = "게시글 제목", example = "멋사 화이팅")
        String title,
        @Schema(description = "게시글 내용", example = "재밌어요 멋사")
        String body,
        @Schema(description = "썸네일 이미지가 존재할 경우, 이미지 url", example = "https://s3.~")
        String thumbnail,
        @Schema(description = "게시글 메인 카테고리", example = "자유게시판")
        String mainCategory,
        @Schema(description = "게시글 서브 카테고리", example = "정보공유")
        String subCategory
) {

    public static PostEditResponseDto of(PostEditData postEditData) {
        PostEditResponseDto responseDto = new PostEditResponseDto(
                postEditData.postId(),
                postEditData.title(),
                postEditData.body(),
                postEditData.thumbnail(),
                postEditData.mainCategory().getTitle(),
                postEditData.subCategory().getTitle()
        );
        return responseDto;
    }
}
