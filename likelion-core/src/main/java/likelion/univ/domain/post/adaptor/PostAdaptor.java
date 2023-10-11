package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Adaptor
@RequiredArgsConstructor
public class PostAdaptor {

    @Autowired
    private PostRepository postRepository;
//    @Autowired
//    private PostCustomRepository postCustomRepository;


    public void save(Post post) {
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).get(); //예외처리
    }

    public void delete(Post post) {
        postRepository.delete(post); //예외처리
    }

    public Page<Post> findAllByAuthor_Id(Long userId, Pageable pageable){
        return postRepository.findAllByAuthor_Id(userId,pageable);
    }

//    public List<Post> retrievePostPaging(Integer page, Integer limit) {
//        return postCustomRepository.pagingWithCoveringIndex(page, limit);
//    }
}
