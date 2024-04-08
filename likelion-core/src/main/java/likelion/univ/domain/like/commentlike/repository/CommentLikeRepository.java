package likelion.univ.domain.like.commentlike.repository;

import java.util.List;
import java.util.Optional;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.like.commentlike.exception.CommentLikeNotFoundException;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    default CommentLike getById(Long id) {
        return findById(id).orElseThrow(CommentLikeNotFoundException::new);
    }

    List<CommentLike> findByUser(User user);

    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    default CommentLike getByCommentIdAndUserId(Long commentId, Long userId) {
        return findByCommentIdAndUserId(commentId, userId)
                .orElseThrow(CommentLikeNotFoundException::new);
    }

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);
}
