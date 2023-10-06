package likelion.univ.post.repository;

import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostReadRepositoryCustom {

    List<PostDetailResponseDto> findAuthorPosts(Long authorId, Pageable pageable);

    List<PostDetailResponseDto> findCommentedPosts(Long authorId, Pageable pageable);

    List<PostDetailResponseDto> findLikedPosts(Pageable pageable);
}
