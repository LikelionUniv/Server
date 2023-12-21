package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<Post> findByCommentAuthorId(Long userId, Pageable pageable);
    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable);
    Page<Post> findByCategoriesOrderByCreatedDate(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    Page<Post> findByCategoriesOrderByLikeCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    Page<Post> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    Page<PostSimpleData> findByCategoriesAndSearchTitle(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    Page<PostSimpleData> findBySearchTitle(String searchTitle, Pageable pageable);
    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable, String sort, String search);
}
