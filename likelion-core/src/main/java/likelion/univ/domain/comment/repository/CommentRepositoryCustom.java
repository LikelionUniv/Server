package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
//    List<ChildCommentData> findChildCommentsByPostId(Long postId);
    List<Comment> findChildCommentsByPostId(Long postId);
//    List<ParentCommentData> findParentCommentsByPostId(Long postId);
    List<Comment> findParentCommentsByPostId(Long postId);
}
