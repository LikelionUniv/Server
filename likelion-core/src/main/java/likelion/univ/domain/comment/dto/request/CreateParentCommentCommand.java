package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateParentCommentCommand {
    private Long postId;
    private Long loginUserId;
    private String body;
}
