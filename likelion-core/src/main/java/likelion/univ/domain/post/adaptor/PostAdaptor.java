package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
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

    public Long save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoudException());
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
    public List<PostSimpleResponseDto> findLikedPosts(Long userId, Pageable pageable) {
        return postRepository.findLikedPosts(userId, pageable);
    }

    public List<PostSimpleResponseDto> findCommentedPosts(Long userId, Pageable pageable) {
        return postRepository.findCommentedPosts(userId, pageable);
    }

    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        return postRepository.findByPostLikeAuthorId(userId,pageable);
    }

    public List<PostSimpleResponseDto> findAllByCategories(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return postRepository.findAllByCategories(mainCategory, subCategory, pageable);
    }

    public List<PostSimpleResponseDto> findPostsByAuthorId(Long userId, Pageable pageable) {
        return postRepository.findPostsByAuthorId(userId, pageable);
    }

}
