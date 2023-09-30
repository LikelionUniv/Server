package likelion.univ.domain.comment.dto;

import likelion.univ.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentCommandResponseDto {
    private Long commentId;

    public static CommentCommandResponseDto of(Long commentId) {
        return CommentCommandResponseDto.builder()
                .commentId(commentId)
                .build();
    }
}
