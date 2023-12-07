package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
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
    public List<PostSimpleData> findLikedPosts(Long userId, Pageable pageable) {
        return postRepository.findLikedPosts(userId, pageable);
    }

    public List<PostSimpleData> findCommentedPosts(Long userId, Pageable pageable) {
        return postRepository.findCommentedPosts(userId, pageable);
    }

    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        return postRepository.findByPostLikeAuthorId(userId,pageable);
    }

    public List<PostSimpleData> findAllByCategories(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findAllByCategories(mainCategory, subCategory, pageable);
    }

    public List<PostSimpleData> findPostsByAuthorId(Long userId, Pageable pageable) {
        return postRepository.findPostsByAuthorId(userId, pageable);
    }

}
