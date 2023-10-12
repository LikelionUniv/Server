package likelion.univ.domain.post.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.post.repository.LikePostRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class LikePostAdaptor {
    private final LikePostRepository likePostRepository;

    public Long countByPostId(Long postId){
        return likePostRepository.countByPostId(postId);
    }
}
