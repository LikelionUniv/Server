package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findCommentsByPostId(Long postId);
}
