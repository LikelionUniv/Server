package likelion.univ.follow.service;

import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.follow.entity.FollowNum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowNumRedisService {
    private final FollowNumRedisDao followNumRedisDao;

    public FollowNum save(Long userId, Long followerNum, Long followingNum){
        FollowNum followNum = FollowNum.builder()
                .followerNum(followerNum)
                .followingNum(followingNum)
                .build();
        followNumRedisDao.save(userId, followNum);
        return followNum;
    }
    public void followerUp(Long userId, FollowNum followNum){
        FollowNum newFollowNum = FollowNum.builder()
                .followerNum(followNum.getFollowerNum() + 1)
                .followingNum(followNum.getFollowingNum())
                .build();
        followNumRedisDao.save(userId, newFollowNum);
    }
    public void followerDown(Long userId, FollowNum followNum){
        FollowNum newFollowNum = FollowNum.builder()
                .followerNum(followNum.getFollowerNum() - 1)
                .followingNum(followNum.getFollowingNum())
                .build();
        followNumRedisDao.save(userId, newFollowNum);
    }
    public void followingUp(Long userId, FollowNum followNum){
        FollowNum newFollowNum = FollowNum.builder()
                .followerNum(followNum.getFollowerNum())
                .followingNum(followNum.getFollowingNum() + 1)
                .build();
        followNumRedisDao.save(userId, newFollowNum);
    }
    public void followingDown(Long userId, FollowNum followNum){
        FollowNum newFollowNum = FollowNum.builder()
                .followerNum(followNum.getFollowerNum())
                .followingNum(followNum.getFollowingNum() - 1)
                .build();
        followNumRedisDao.save(userId, newFollowNum);
    }
}