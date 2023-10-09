package likelion.univ.domain.like.commentlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentLikeCreateServiceDto {
    private Long loginUserId;
    private Long commentId;
}
