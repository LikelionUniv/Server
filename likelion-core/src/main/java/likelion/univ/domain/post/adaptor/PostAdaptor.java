package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.exception.PostNotFoudException;
import likelion.univ.domain.post.repository.PostCommandRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {

    private final PostCommandRepository postRepository;


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

}
