package likelion.univ.domain.like.postlike.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.like.postlike.exception.PostLikeNotFoundException;
import likelion.univ.domain.like.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class PostLikeAdaptor {
    private final PostLikeRepository postLikeRepository;

    public Boolean existsByPostIdAndAuthorId(Long postId, long userId) {
        return postLikeRepository.existsByPostIdAndAuthorId(postId, userId);
    }

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

    public PostLike findById(Long postLikeId) {
        return postLikeRepository.findById(postLikeId).orElseThrow(() -> new PostLikeNotFoundException());
    }
    public Long countByPostId(Long postId){
        return postLikeRepository.countByPostId(postId);
    }
}
