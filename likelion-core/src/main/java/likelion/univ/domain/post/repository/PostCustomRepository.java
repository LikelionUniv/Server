package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCustomRepository {
    Page<Post> findByCommentAuthorId(Long userId, Pageable pageable);
    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable);
    List<PostDetailResponseDto> findAllByCategories(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);
    List<PostDetailResponseDto> findPostsByAuthorId(Long authorId, Pageable pageable);

    List<PostDetailResponseDto> findCommentedPosts(Long loginUserId, Pageable pageable);

    List<PostDetailResponseDto> findLikedPosts(Long loginUserId, Pageable pageable);
}
