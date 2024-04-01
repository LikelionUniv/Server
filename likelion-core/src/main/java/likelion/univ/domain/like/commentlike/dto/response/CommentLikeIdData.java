package likelion.univ.domain.like.commentlike.dto.response;

import likelion.univ.domain.like.commentlike.entity.CommentLike;
import lombok.Builder;

@Builder
public record CommentLikeIdData(
        Long commentLikeId
) {

    public static CommentLikeIdData of(Long commentLikeId) {
        return CommentLikeIdData.builder()
                .commentLikeId(commentLikeId)
                .build();
    }

    public static CommentLikeIdData of(CommentLike commentLike) {
        return CommentLikeIdData.builder()
                .commentLikeId(commentLike.getId())
                .build();
    }
}
