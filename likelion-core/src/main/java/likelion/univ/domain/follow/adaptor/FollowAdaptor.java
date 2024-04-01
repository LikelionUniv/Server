package likelion.univ.domain.follow.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.follow.exception.AlreadyFollowingUserException;
import likelion.univ.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class FollowAdaptor {
    private final FollowRepository followRepository;

    public Boolean hasFollowedUser(Long followerId, Long followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    public Long countByFollowerId(Long followerId) {
        return followRepository.countByFollowerId(followerId);
    }

    public Long countByFollowingId(Long followingId) {
        return followRepository.countByFollowingId(followingId);
    }

    public void save(Long currentUserId, Long userId) {
        try {
            followRepository.save(currentUserId, userId);
        } catch (Exception e) {
            throw new AlreadyFollowingUserException();
        }
    }

    public void delete(Long currentUserId, Long userId) {
        followRepository.delete(currentUserId, userId);
    }
}
