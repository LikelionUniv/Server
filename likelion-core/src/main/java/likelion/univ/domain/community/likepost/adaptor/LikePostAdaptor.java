package likelion.univ.domain.community.likepost.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.community.likepost.entity.LikePost;
import likelion.univ.domain.community.likepost.exception.LikePostNotFoundException;
import likelion.univ.domain.community.likepost.repository.LikePostRepository;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
@RequiredArgsConstructor
public class LikePostAdaptor {

    @Autowired
    private LikePostRepository likePostRepository;

    public void save(LikePost likePost) {
        likePostRepository.save(likePost);
    }
    public void delete(LikePost likePost) {
        likePostRepository.delete(likePost);
    }

    public LikePost find(Post post, User author) {
        return likePostRepository.findByPostAndAuthor(post, author).orElseThrow(() -> new LikePostNotFoundException());
    }
}
