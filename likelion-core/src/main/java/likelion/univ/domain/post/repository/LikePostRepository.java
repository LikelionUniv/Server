package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost,Long> {
    Long countByPostId(Long postId);
}
