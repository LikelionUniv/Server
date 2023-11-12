package likelion.univ.domain.like.commentlike.dto;

import likelion.univ.domain.like.commentlike.entity.CommentLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentLikeResponseDto {
    private Long commentLikeId;

    public static CommentLikeResponseDto of(Long commentLikeId) {
        return CommentLikeResponseDto.builder()
                .commentLikeId(commentLikeId)
                .build();
    }

    public static CommentLikeResponseDto of(CommentLike commentLike) {
        return CommentLikeResponseDto.builder()
                .commentLikeId(commentLike.getId())
                .build();
    }
}
