package likelion.univ.domain.post.repository;

import likelion.univ.common.query.BaseQueryDslRepository;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.dto.response.PostEditData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository extends BaseQueryDslRepository {

    PostEditData findPostEditByPostId(Long postId);

    Page<Post> findByCommentAuthorId(Long userId, Pageable pageable);

    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable);

    Page<Post> findByCategoriesOrderByCreatedDate(MainCategory mainCategory, SubCategory subCategory,
                                                  Pageable pageable);

    Page<Post> findByCategoriesOrderByLikeCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable);

    Page<Post> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory,
                                                   Pageable pageable);

    Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByCommentCount(String searchTitle,
                                                                           MainCategory mainCategory,
                                                                           SubCategory subCategory, Pageable pageable);

    Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByLikeCount(String searchTitle, MainCategory mainCategory,
                                                                        SubCategory subCategory, Pageable pageable);

    Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByCreatedDate(String searchTitle, MainCategory mainCategory,
                                                                          SubCategory subCategory, Pageable pageable);

    Page<PostSimpleData> findBySearchTitleOrderByCreatedDate(String searchTitle, Pageable pageable);

    Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable, String sort, String search);

    Page<Post> findByCategoriesAndUniversityOrderByCreatedDate(
            MainCategory mainCategory, SubCategory subCategory, Long universityId, Pageable pageable);

    Page<PostSimpleData> findBySearchTitleOrderByCommentCount(String searchTitle, Pageable pageable);

    Page<PostSimpleData> findBySearchTitleOrderByLikeCount(String searchTitle, Pageable pageable);
}
