package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;

import java.util.List;

public interface CommentRepositoryCustom {
    List<ChildCommentData> findChildCommentsByPostId(Long postId);
    List<ParentCommentData> findParentCommentsByPostId(Long postId);
}
