package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.exception.PostNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    default Post getById(Long postId) {
        return findById(postId).orElseThrow(PostNotFoundException::new);
    }

    Page<Post> findAllByAuthorId(Long userId, Pageable pageable);
}
