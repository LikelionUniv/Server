package likelion.univ.post.dao;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.post.entity.PostCountInfo;
import org.springframework.data.redis.core.RedisTemplate;

import static likelion.univ.common.constant.RedisKey.POST_COUNT_INFO;
import static likelion.univ.common.constant.RedisKey.POST_COUNT_INFO_EXPIRE_SEC;

@RedisRepository
public class PostCountInfoRedisDao extends BaseRedisRepository<PostCountInfo> {

    public PostCountInfoRedisDao(RedisTemplate redisTemplate) {
        this.prefix = POST_COUNT_INFO + ":";
        this.ttl = POST_COUNT_INFO_EXPIRE_SEC;
        this.redisTemplate = redisTemplate;
    }
}
