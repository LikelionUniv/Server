package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.exception.PostNotFoudException;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

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
        postRepository.delete(post); //예외처리
    }

    public List<Post> findPosts(Integer page, Integer limit) {
        return postRepository.pagingWithCoveringIndex(page, limit);
    }
}
