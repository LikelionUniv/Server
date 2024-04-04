package likelion.univ.domain.like.postlike.repository;

import java.util.Optional;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long>, PostLikeCustomRepository {

    Optional<PostLike> findByPostAndUser(Post post, User user);

    Long countByPostId(Long postId);

    Boolean existsByPostIdAndUserId(Long postId, Long userId);
}
