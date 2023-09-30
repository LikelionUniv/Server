package likelion.univ.domain.commentlike.repository;

import likelion.univ.domain.commentlike.entity.CommentLike;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<CommentLike> findByUser(User user);
}
