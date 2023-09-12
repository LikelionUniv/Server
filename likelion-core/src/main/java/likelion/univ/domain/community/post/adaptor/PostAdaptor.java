package likelion.univ.domain.community.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.community.post.entity.Post;
//import likelion.univ.domain.community.post.repository.PostCustomRepository;
import likelion.univ.domain.community.post.exception.PostNotFoudException;
import likelion.univ.domain.community.post.repository.PostCustomRepository;
import likelion.univ.domain.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCustomRepository postCustomRepository;


    public void save(Post post) {
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoudException());
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> retrievePostPaging(Integer page, Integer limit) {
        return postCustomRepository.pagingWithCoveringIndex(page, limit);
    }
}
