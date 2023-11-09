package likelion.univ.domain.follow.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.follow.entity.Follow;
import likelion.univ.domain.follow.repository.FollowRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class FollowAdaptor {
    private final FollowRepository followRepository;

    public Long countByFollowerId(Long followerId){
        return followRepository.countByFollowerId(followerId);
    }
    public Long countByFollowingId(Long followingId){
        return followRepository.countByFollowingId(followingId);
    }
}
