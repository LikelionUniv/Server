package likelion.univ.domain.comment.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record CommentData(
        Long postAuthorId,
        List<ParentCommentData> parentComments,
        List<ChildCommentData> childComments
) {
}
