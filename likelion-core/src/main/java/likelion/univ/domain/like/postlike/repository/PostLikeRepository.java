package likelion.univ.domain.like.postlike.repository;

import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<PostLike> findByPostAndAuthor(Post post, User author);
<<<<<<< HEAD
=======
    Long countByPostId(Long postId);
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
}
