package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCustomRepository {
    Page<Post> findByCommentAuthorId(Long userId, Pageable pageable);
    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable);
    List<PostSimpleResponseDto> findAllByCategories(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);
    List<PostSimpleResponseDto> findPostsByAuthorId(Long authorId, Pageable pageable);

    List<PostSimpleResponseDto> findCommentedPosts(Long loginUserId, Pageable pageable);

    List<PostSimpleResponseDto> findLikedPosts(Long loginUserId, Pageable pageable);
}
