package likelion.univ.post.repository;

import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostReadRepositoryCustom {

    List<PostDetailResponseDto> findAll(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    List<PostDetailResponseDto> findAuthorPosts(Long authorId, Pageable pageable);

    List<PostDetailResponseDto> findCommentedPosts(Long authorId, Pageable pageable);

    List<PostDetailResponseDto> findLikedPosts(Pageable pageable);
}
