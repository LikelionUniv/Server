package likelion.univ.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public record CommentData(
    Long postAuthorId,
    List<ParentCommentData> parentComments,
    List<ChildCommentData> childComments
) {

}
