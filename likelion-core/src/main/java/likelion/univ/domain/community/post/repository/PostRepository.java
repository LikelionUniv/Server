package likelion.univ.domain.community.post.repository;

import likelion.univ.domain.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
