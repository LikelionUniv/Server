package likelion.univ.domain.comment.repository;

import java.util.List;
import likelion.univ.domain.comment.entity.Comment;

public interface CommentRepositoryCustom {
    List<Comment> findParentCommentsByPostId(Long postId);

    List<Comment> findChildCommentsByPostId(Long postId);

    Long countByPostIdAndIsDeletedEquals(Long postId, Boolean isDeleted);

}
