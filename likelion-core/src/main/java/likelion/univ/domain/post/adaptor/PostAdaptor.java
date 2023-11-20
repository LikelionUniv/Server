package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
<<<<<<< HEAD
import likelion.univ.domain.post.exception.PostNotFoudException;
import likelion.univ.domain.post.repository.PostCommandRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
=======
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import likelion.univ.domain.post.exception.PostNotFoudException;
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {
    private final PostRepository postRepository;

<<<<<<< HEAD
    private final PostCommandRepository postRepository;


=======
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
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

<<<<<<< HEAD
=======
    public Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable){
        return postRepository.findAllByAuthor_Id(userId,pageable);
    }

    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        return postRepository.findByCommentAuthorId(userId,pageable);
    }

    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        return postRepository.findByPostLikeAuthorId(userId,pageable);
    }
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
}
