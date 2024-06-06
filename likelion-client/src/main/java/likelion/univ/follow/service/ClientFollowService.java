package likelion.univ.follow.service;

import likelion.univ.domain.follow.exception.AlreadyFollowingUserException;
import likelion.univ.domain.follow.repository.FollowRepository;
import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientFollowService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final FollowRepository followRepository;
    private final FollowNumRedisDao followNumRedisDao;
    private final FollowNumRedisService followNumRedisService;

    public void follow(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        try {
            followRepository.save(currentUserId, userId);
        } catch (Exception e) {
            throw new AlreadyFollowingUserException();
        }
        followNumRedisDao.findById(currentUserId)
                .ifPresent(it -> followNumRedisService.followingUp(currentUserId, it));
        followNumRedisDao.findById(userId)
                .ifPresent(it -> followNumRedisService.followerUp(userId, it));
    }

    public void cancelFollow(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        followRepository.delete(currentUserId, userId);
        followNumRedisDao.findById(currentUserId)
                .ifPresent(it -> followNumRedisService.followingDown(currentUserId, it));
        followNumRedisDao.findById(userId)
                .ifPresent(it -> followNumRedisService.followerDown(userId, it));
    }
}
