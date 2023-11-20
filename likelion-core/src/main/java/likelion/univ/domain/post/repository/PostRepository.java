package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable);
}
