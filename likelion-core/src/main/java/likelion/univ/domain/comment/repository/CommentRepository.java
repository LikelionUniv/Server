package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c from Comment c JOIN FETCH c.author u JOIN FETCH c.post p JOIN FETCH c.parentComment pc")
    List<Comment> findByPost(Post post);

    Long countByPostId(Long postId);
}
