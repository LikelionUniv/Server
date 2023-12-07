package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeleteCommentCommand {
    private Long loginUserId;
    private Long commentId;
}
