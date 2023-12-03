package likelion.univ.domain.post.dto.request;

public record GetPostDetailServiceDto(
        Long postId,
        Long loginUserId
) {

}
