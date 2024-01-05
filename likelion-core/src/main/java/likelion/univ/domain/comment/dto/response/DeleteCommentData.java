package likelion.univ.domain.comment.dto.response;

public record DeleteCommentData(
        Boolean isDeleted,
        Long postId
) {
}
