package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.user.dao.UserFollowNumRedisDao;
import likelion.univ.user.dto.response.ProfileDetailsDto;
import likelion.univ.user.entity.UserFollowNum;
import likelion.univ.user.service.UserFollowNumRedisService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetProfileUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final UserAdaptor userAdaptor;
    private final UserFollowNumRedisDao userFollowNumRedisDao;
    private final UserFollowNumRedisService userFollowNumRedisService;
    private final FollowAdaptor followAdaptor;

    public ProfileDetailsDto execute(Long userId){
        Long currentUserId = authentiatedUserUtils.getCurrentUserId();
        User user = userAdaptor.findByIdWithUniversity(userId);

        return createDto(user, currentUserId);
    }

    private ProfileDetailsDto createDto(User user, Long currentUserId){
        UserFollowNum userFollowNum = getUserFollowNum(user.getId());

        if (user.getId().equals(currentUserId))
            return ProfileDetailsDto.of(user, true, userFollowNum.getFollowerNum(), userFollowNum.getFollowingNum());
        else return ProfileDetailsDto.of(user, false, userFollowNum.getFollowerNum(), userFollowNum.getFollowingNum());
    }

    private UserFollowNum getUserFollowNum(Long userId){
        Optional<UserFollowNum> userFollowNum = userFollowNumRedisDao.findById(userId);
        if(userFollowNum.isEmpty()){
            Long followerNum = followAdaptor.countByFollowingId(userId);
            Long followingNum = followAdaptor.countByFollowerId(userId);
            return userFollowNumRedisService.save(userId, followerNum, followingNum);
        }else return userFollowNum.get();
    }
}
