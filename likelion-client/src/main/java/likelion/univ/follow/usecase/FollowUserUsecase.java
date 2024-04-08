package likelion.univ.follow.usecase;

import java.util.Optional;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.follow.exception.AlreadyFollowingUserException;
import likelion.univ.domain.follow.repository.FollowRepository;
import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.follow.entity.FollowNum;
import likelion.univ.follow.service.FollowNumRedisService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FollowUserUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final FollowRepository followRepository;
    private final FollowNumRedisDao followNumRedisDao;
    private final FollowNumRedisService followNumRedisService;

    public void execute(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        try {
            followRepository.save(currentUserId, userId);
        } catch (Exception e) {
            throw new AlreadyFollowingUserException();
        }
        updateFollowNumRedis(currentUserId, userId);
    }

    private void updateFollowNumRedis(Long currentUserId, Long userId) {
        Optional<FollowNum> myFollowNum = followNumRedisDao.findById(currentUserId);
        if (myFollowNum.isPresent()) {
            followNumRedisService.followingUp(currentUserId, myFollowNum.get());
        }
        Optional<FollowNum> targetFollowNum = followNumRedisDao.findById(userId);
        if (targetFollowNum.isPresent()) {
            followNumRedisService.followerUp(userId, targetFollowNum.get());
        }
    }
}
