package likelion.univ.domain.follow.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class FolllowAdaptor {
    private final FollowRepository followRepository;

    public Long countByFollowerId(Long followerId){
        return followRepository.countByFollowerId(followerId);
    }

    public Long countByFolloweeId(Long followeeId){
        return followRepository.countByFolloweeId(followeeId);
    }

}
