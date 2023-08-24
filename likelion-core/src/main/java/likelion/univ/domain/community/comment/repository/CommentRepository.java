package likelion.univ.domain.community.comment.repository;

import likelion.univ.domain.community.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
