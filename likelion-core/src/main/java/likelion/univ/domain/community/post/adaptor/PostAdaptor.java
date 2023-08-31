package likelion.univ.domain.community.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {

    @Autowired
    private PostRepository postRepository;


    public void save(Post post) {
        postRepository.save(post);
    }
}
