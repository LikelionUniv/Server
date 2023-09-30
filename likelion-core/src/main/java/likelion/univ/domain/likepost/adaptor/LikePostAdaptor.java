package likelion.univ.domain.likepost.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.likepost.entity.LikePost;
import likelion.univ.domain.likepost.exception.LikePostNotFoundException;
import likelion.univ.domain.likepost.repository.LikePostRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
@RequiredArgsConstructor
public class LikePostAdaptor {
    private final LikePostRepository likePostRepository;

    public Long save(LikePost likePost) {
        LikePost savedLike = likePostRepository.save(likePost);
        return savedLike.getId();
    }
    public void delete(LikePost likePost) {
        likePostRepository.delete(likePost);
    }

    public LikePost find(Post post, User author) {
        return likePostRepository.findByPostAndAuthor(post, author).orElseThrow(() -> new LikePostNotFoundException());
    }
}
