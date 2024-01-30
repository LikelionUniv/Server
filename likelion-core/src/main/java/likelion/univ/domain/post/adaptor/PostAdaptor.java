package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.dto.response.PostEditData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import likelion.univ.domain.post.exception.PostNotFoudException;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {
    private final PostRepository postRepository;

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoudException::new);
    }

    public PostEditData findPostEditByPostId(Long postId) {
        return postRepository.findPostEditByPostId(postId);
    }

    public Long save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
    public void deleteAllByIdInBatch(List<Long> ids){
        postRepository.deleteAllByIdInBatch(ids);
    }

    public Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable){
        return postRepository.findAllByAuthor_Id(userId,pageable);
    }

    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        return postRepository.findByCommentAuthorId(userId,pageable);
    }

    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable, String sort, String search){
        return postRepository.findByPostLikeAuthorId(userId,pageable,sort,search);
    }

    public Page<Post> findByCategoriesOrderByCreatedDate(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesOrderByCreatedDate(mainCategory, subCategory, pageable);
    }

    public Page<Post> findByCategoriesOrderByLikeCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesOrderByLikeCount(mainCategory, subCategory, pageable);
    }

    public Page<Post> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesOrderByCommentCount(mainCategory, subCategory, pageable);
    }

    public Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByCreatedDate(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesAndSearchTitleOrderByCreatedDate(searchTitle, mainCategory, subCategory, pageable);
    }
    public Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByCommentCount(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesAndSearchTitleOrderByCommentCount(searchTitle, mainCategory, subCategory, pageable);
    }
    public Page<PostSimpleData> findByCategoriesAndSearchTitleOrderByLikeCount(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesAndSearchTitleOrderByLikeCount(searchTitle, mainCategory, subCategory, pageable);
    }
    public Page<PostSimpleData> findBySearchTitleOrderByCreatedDate(String searchTitle, Pageable pageable) {
        return postRepository.findBySearchTitleOrderByCreatedDate(searchTitle, pageable);
    }
    public Page<PostSimpleData> findBySearchTitleOrderByCommentCount(String searchTitle, Pageable pageable) {
        return postRepository.findBySearchTitleOrderByCommentCount(searchTitle, pageable);
    }
    public Page<PostSimpleData> findBySearchTitleOrderByLikeCount(String searchTitle, Pageable pageable) {
        return postRepository.findBySearchTitleOrderByLikeCount(searchTitle, pageable);
    }

    public Page<Post> findPostsByCategoriesAndUniversityOrderByCreatedDate(
            MainCategory mainCategory, SubCategory subCategory, Long universityId, Pageable pageable){
        return postRepository.findByCategoriesAndUniversityOrderByCreatedDate(
                mainCategory, subCategory, universityId, pageable);
    }
}
