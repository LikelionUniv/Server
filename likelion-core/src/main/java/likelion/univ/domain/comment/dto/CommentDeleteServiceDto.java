package likelion.univ.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDeleteServiceDto {
    private Long loginUserId;
    private Long commentId;
}
