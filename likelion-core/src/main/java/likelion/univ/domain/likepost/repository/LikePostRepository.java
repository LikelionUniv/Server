package likelion.univ.domain.likepost.repository;

import likelion.univ.domain.likepost.entity.LikePost;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikePost,Long> {
    Optional<LikePost> findByPostAndAuthor(Post post, User author);
}
