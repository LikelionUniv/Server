package likelion.univ.post.dto.response;

import likelion.univ.domain.post.dto.response.PostEditData;

public record PostEditResponseDto(
        Long id,
        String title,
        String body,
        String thumbnail,
        String mainCategory,
        String subCategory
) {
    public static PostEditResponseDto of(PostEditData postEditData) {
        PostEditResponseDto responseDto = new PostEditResponseDto(postEditData.postId(), postEditData.title(), postEditData.body(), postEditData.thumbnail(), postEditData.mainCategory().getTitle(), postEditData.subCategory().getTitle());
        return responseDto;
    }
}
