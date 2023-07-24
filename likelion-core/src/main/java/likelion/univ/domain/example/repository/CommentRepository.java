package likelion.univ.domain.example.repository;

import likelion.univ.domain.example.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
