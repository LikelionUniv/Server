package likelion.univ.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentUpdateServiceDto {
    private Long commentId;
    private Long loginUserId;
    private String body;
}
