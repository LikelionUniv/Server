package likelion.univ.refreshtoken.dao;

import static likelion.univ.common.constant.RedisKey.REFRESH_TOKEN;
import static likelion.univ.common.constant.RedisKey.REFRESH_TOKEN_EXPIRE_SEC;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.refreshtoken.entity.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;

@RedisRepository
public class RefreshTokenRedisDao extends BaseRedisRepository<RefreshToken> {

    public RefreshTokenRedisDao(RedisTemplate redisTemplate) {
        this.prefix = REFRESH_TOKEN + ":";
        this.ttl = REFRESH_TOKEN_EXPIRE_SEC;
        this.redisTemplate = redisTemplate;
    }
}
