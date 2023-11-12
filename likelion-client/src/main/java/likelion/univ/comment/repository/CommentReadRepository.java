package likelion.univ.comment.repository;

import likelion.univ.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReadRepository extends JpaRepository<Comment, Long>, CommentReadRepositoryCustom {
}
