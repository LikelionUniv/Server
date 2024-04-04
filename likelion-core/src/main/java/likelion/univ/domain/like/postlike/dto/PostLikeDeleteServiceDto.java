package likelion.univ.domain.like.postlike.dto;

public record PostLikeDeleteServiceDto(
        Long postId,
        Long loginUserId
) {
}
