package likelion.univ.domain.community.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.comment.exception.CommentNotFoundException;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.community.post.repository.PostRepository;
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
        return postRepository.findById(id).get(); //예외처리
    }

    public void delete(Post post) {
        postRepository.delete(post); //예외처리
    }

    public List<Post> retrievePostPaging(Integer page, Integer limit) {
        return postRepository.pagingWithCoveringIndex(page, limit);
    }
}
