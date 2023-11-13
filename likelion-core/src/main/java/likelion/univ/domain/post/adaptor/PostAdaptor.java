package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import likelion.univ.domain.post.exception.PostNotFoudException;

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
        postRepository.delete(post); //예외처리
    }

    public Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable){
        return postRepository.findAllByAuthor_Id(userId,pageable);
    }

    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        return postRepository.findByCommentAuthorId(userId,pageable);
    }
//    public List<Post> retrievePostPaging(Integer page, Integer limit) {
//        return postCustomRepository.pagingWithCoveringIndex(page, limit);
//    }
}
