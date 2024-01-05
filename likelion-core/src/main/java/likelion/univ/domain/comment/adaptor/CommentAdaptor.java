package likelion.univ.domain.comment.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.CommentNotFoundException;
import likelion.univ.domain.comment.repository.CommentRepository;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Long save(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public List<Comment> findParentCommentsByPostId(Long postId) {
        return commentRepository.findParentCommentsByPostId(postId);
    }

    public List<Comment> findChildCommentsByPostId(Long postId) {
        return commentRepository.findChildCommentsByPostId(postId);
    }

    public Long countByPostId(Long postId){
        return commentRepository.countByPostId(postId);
    }

    public Long countByPostIdAndIsDeletedEquals(Long postId, Boolean isDeleted) {
        return commentRepository.countByPostIdAndIsDeletedEquals(postId, isDeleted);
    }
    public Comment findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
