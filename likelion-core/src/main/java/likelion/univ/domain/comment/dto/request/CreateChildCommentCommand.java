package likelion.univ.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateChildCommentCommand {
    private Long parentCommentId;
    private Long loginUserId;
    private String body;
}
