package likelion.univ.domain.like.commentlike.repository;

import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long>, CommentLikeRepositoryCustom {

    List<CommentLike> findByUser(User user);
    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);
    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);

}
