package likelion.univ.user.service;

import likelion.univ.user.dao.UserFollowNumRedisDao;
import likelion.univ.user.entity.UserFollowNum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowNumRedisService {
    private final UserFollowNumRedisDao userFollowNumRedisDao;

    public UserFollowNum save(Long userId, Long followerNum, Long followingNum){
        UserFollowNum userFollowNum = UserFollowNum.builder()
                .followerNum(followerNum)
                .followingNum(followingNum)
                .build();
        userFollowNumRedisDao.save(userId, userFollowNum);
        return userFollowNum;
    }
}