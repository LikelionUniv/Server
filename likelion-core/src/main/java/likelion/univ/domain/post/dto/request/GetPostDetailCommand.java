package likelion.univ.domain.post.dto.request;

public record GetPostDetailCommand(
        Long postId,
        Long loginUserId
) {
}
