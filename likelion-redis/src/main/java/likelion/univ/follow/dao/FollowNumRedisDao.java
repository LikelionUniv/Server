package likelion.univ.follow.dao;

import static likelion.univ.common.constant.RedisKey.USER_FOLLOW_NUM;
import static likelion.univ.common.constant.RedisKey.USER_FOLLOW_NUM_EXPIRE_SEC;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.follow.entity.FollowNum;
import org.springframework.data.redis.core.RedisTemplate;

@RedisRepository
public class FollowNumRedisDao extends BaseRedisRepository<FollowNum> {

    public FollowNumRedisDao(RedisTemplate redisTemplate) {
        this.prefix = USER_FOLLOW_NUM + ":";
        this.ttl = USER_FOLLOW_NUM_EXPIRE_SEC;
        this.redisTemplate = redisTemplate;
    }
}
