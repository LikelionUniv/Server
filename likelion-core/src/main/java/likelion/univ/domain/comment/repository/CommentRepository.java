package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    Long countByPostId(Long postId);

    Comment findByPostId(Long postId);

}
