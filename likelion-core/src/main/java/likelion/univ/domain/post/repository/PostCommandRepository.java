package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommandRepository extends JpaRepository<Post, Long>, PostCommandCustomRepository {


}
