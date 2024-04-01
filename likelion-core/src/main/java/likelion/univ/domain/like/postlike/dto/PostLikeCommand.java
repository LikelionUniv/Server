package likelion.univ.domain.like.postlike.dto;

public record PostLikeCommand(
        Long postId,
        Long loginUserId
) {

}
