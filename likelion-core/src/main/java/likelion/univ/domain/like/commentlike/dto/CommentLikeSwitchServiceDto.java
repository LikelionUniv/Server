package likelion.univ.domain.like.commentlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentLikeSwitchServiceDto {
    private Long commentLikeId;
    private Long loginUserId;
}
