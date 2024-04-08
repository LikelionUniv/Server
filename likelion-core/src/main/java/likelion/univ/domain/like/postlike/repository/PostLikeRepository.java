package likelion.univ.domain.like.postlike.repository;

import java.util.Optional;
import likelion.univ.domain.like.commentlike.exception.CommentLikeNotFoundException;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.like.postlike.exception.PostLikeNotFoundException;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long>, PostLikeCustomRepository {

    default PostLike getById(Long postLikeId) {
        return findById(postLikeId).orElseThrow(PostLikeNotFoundException::new);
    }

    default PostLike getByPostAndUser(Post post, User user) {
        return findByPostAndUser(post, user).orElseThrow(CommentLikeNotFoundException::new);
    }

    Optional<PostLike> findByPostAndUser(Post post, User user);

    Long countByPostId(Long postId);

    Boolean existsByPostIdAndUserId(Long postId, Long userId);
}
