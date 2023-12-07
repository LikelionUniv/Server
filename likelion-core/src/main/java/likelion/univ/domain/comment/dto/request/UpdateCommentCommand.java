package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateCommentCommand {
    private Long commentId;
    private Long loginUserId;
    private String body;
}
