package likelion.univ.refreshtoken.dao;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.refreshtoken.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@RedisRepository
public class RefreshTokenRedisDao extends BaseRedisRepository<RefreshToken> {
    @Autowired
    public RefreshTokenRedisDao(RedisTemplate redisTemplate) {
        this.prefix = "refreshToken:";
        this.redisTemplate = redisTemplate;
    }
}
