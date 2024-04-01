package likelion.univ.follow.usecase;

import java.util.Optional;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.follow.entity.FollowNum;
import likelion.univ.follow.service.FollowNumRedisService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CancelFollowUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final FollowAdaptor followAdaptor;
    private final FollowNumRedisDao followNumRedisDao;
    private final FollowNumRedisService followNumRedisService;

    public void execute(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        followAdaptor.delete(currentUserId, userId);
        updateFollowNumRedis(currentUserId, userId);
    }

    private void updateFollowNumRedis(Long currentUserId, Long userId) {
        Optional<FollowNum> myFollowNum = followNumRedisDao.findById(currentUserId);
        if (myFollowNum.isPresent()) {
            followNumRedisService.followingDown(currentUserId, myFollowNum.get());
        }
        Optional<FollowNum> targetFollowNum = followNumRedisDao.findById(userId);
        if (targetFollowNum.isPresent()) {
            followNumRedisService.followerDown(userId, targetFollowNum.get());
        }
    }
}
