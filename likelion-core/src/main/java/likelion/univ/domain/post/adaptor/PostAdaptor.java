package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.exception.PostNotFoudException;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {

    @Autowired
    private PostRepository postRepository;


    public void save(Post post) {
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoudException());
    }

    public void delete(Post post) {
        postRepository.delete(post); //예외처리
    }

    public List<Post> retrievePostPaging(Integer page, Integer limit) {
        return postRepository.pagingWithCoveringIndex(page, limit);
    }
}
