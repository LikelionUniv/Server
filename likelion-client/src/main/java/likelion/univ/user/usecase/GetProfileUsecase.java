package likelion.univ.user.usecase;

import java.util.Optional;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.follow.entity.FollowNum;
import likelion.univ.follow.service.FollowNumRedisService;
import likelion.univ.user.dto.response.ProfileDetailsDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetProfileUsecase {
    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserAdaptor userAdaptor;
    private final FollowNumRedisDao followNumRedisDao;
    private final FollowNumRedisService followNumRedisService;
    private final FollowAdaptor followAdaptor;

    public ProfileDetailsDto execute(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        User user = userAdaptor.findByIdWithUniversity(userId);

        return createDto(user, currentUserId);
    }

    private ProfileDetailsDto createDto(User user, Long currentUserId) {
        FollowNum followNum = getUserFollowNum(user.getId());

        if (user.getId().equals(currentUserId)) {
            return ProfileDetailsDto.of(user, true, followNum.getFollowerNum(), followNum.getFollowingNum());
        } else {
            return ProfileDetailsDto.of(user, false, followNum.getFollowerNum(), followNum.getFollowingNum());
        }
    }

    private FollowNum getUserFollowNum(Long userId) {
        Optional<FollowNum> userFollowNum = followNumRedisDao.findById(userId);
        if (userFollowNum.isEmpty()) {
            Long followerNum = followAdaptor.countByFollowingId(userId);
            Long followingNum = followAdaptor.countByFollowerId(userId);
            return followNumRedisService.save(userId, followerNum, followingNum);
        } else {
            return userFollowNum.get();
        }
    }
}
