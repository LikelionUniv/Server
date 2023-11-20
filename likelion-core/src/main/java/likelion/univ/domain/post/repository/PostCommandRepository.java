package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD:likelion-core/src/main/java/likelion/univ/domain/post/repository/PostCommandRepository.java

public interface PostCommandRepository extends JpaRepository<Post, Long>, PostCommandCustomRepository {


=======
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable);
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484:likelion-core/src/main/java/likelion/univ/domain/post/repository/PostRepository.java
}
