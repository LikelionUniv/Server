package likelion.univ.domain.community.comment.repository;

import likelion.univ.domain.community.comment.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
}
