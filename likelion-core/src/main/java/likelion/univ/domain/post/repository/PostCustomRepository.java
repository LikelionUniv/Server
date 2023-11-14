package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable);
    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable);
}
