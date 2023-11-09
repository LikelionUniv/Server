package likelion.univ.user.dao;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.user.entity.UserFollowNum;
import org.springframework.data.redis.core.RedisTemplate;

import static likelion.univ.common.constant.RedisKey.USER_FOLLOW_NUM;
import static likelion.univ.common.constant.RedisKey.USER_FOLLOW_NUM_EXPIRE_SEC;

@RedisRepository
public class UserFollowNumRedisDao extends BaseRedisRepository<UserFollowNum> {

    public UserFollowNumRedisDao(RedisTemplate redisTemplate) {
        this.prefix = USER_FOLLOW_NUM + ":";
        this.ttl = USER_FOLLOW_NUM_EXPIRE_SEC;
        this.redisTemplate = redisTemplate;
    }
}