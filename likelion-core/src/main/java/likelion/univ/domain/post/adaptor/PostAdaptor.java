package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import likelion.univ.domain.post.exception.PostNotFoudException;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {
    private final PostRepository postRepository;

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoudException::new);

    }

    public Long save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable){
        return postRepository.findAllByAuthor_Id(userId,pageable);
    }

    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        return postRepository.findByCommentAuthorId(userId,pageable);
    }

    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        return postRepository.findByPostLikeAuthorId(userId,pageable);
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

    public Page<PostSimpleData> findByCategoriesAndSearchTitle(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findByCategoriesAndSearchTitle(searchTitle, mainCategory, subCategory, pageable);
    }

    public Page<PostSimpleData> findBySearchTitle(String searchTitle, Pageable pageable) {
        return postRepository.findBySearchTitle(searchTitle, pageable);
    }
}
