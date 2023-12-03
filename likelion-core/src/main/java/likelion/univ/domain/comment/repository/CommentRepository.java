package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.dto.ParentCommentDetailResponseDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    Long countByPostId(Long postId);

}
