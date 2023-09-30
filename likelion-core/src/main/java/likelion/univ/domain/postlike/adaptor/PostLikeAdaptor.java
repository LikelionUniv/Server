package likelion.univ.domain.postlike.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.postlike.entity.PostLike;
import likelion.univ.domain.postlike.exception.PostLikeNotFoundException;
import likelion.univ.domain.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class PostLikeAdaptor {
    private final PostLikeRepository postLikeRepository;

    public Long save(PostLike postLike) {
        PostLike savedLike = postLikeRepository.save(postLike);
        return savedLike.getId();
    }
    public void delete(PostLike postLike) {
        postLikeRepository.delete(postLike);
    }

    public PostLike find(Post post, User author) {
        return postLikeRepository.findByPostAndAuthor(post, author).orElseThrow(() -> new PostLikeNotFoundException());
    }
}
