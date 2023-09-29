package likelion.univ.domain.likecomment.repository;

import likelion.univ.domain.likecomment.entity.LikeComment;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {

    List<LikeComment> findByUser(User user);
}
