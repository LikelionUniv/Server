package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.CommentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    default Comment getById(Long id) {
        return findById(id).orElseThrow(CommentNotFoundException::new);
    }

    Long countByPostId(Long postId);
}
