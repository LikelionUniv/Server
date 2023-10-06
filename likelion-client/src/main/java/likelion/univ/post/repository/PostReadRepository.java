package likelion.univ.post.repository;

import likelion.univ.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReadRepository extends JpaRepository<Post, Long>, PostReadRepositoryCustom {

}
