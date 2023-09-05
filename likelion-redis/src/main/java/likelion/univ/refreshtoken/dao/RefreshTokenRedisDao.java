package likelion.univ.refreshtoken.dao;

import likelion.univ.annotation.RedisRepository;
import likelion.univ.common.base.BaseRedisRepository;
import likelion.univ.refreshtoken.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static likelion.univ.common.constant.RedisKey.REFRESH_TOKEN;

@RedisRepository
public class RefreshTokenRedisDao extends BaseRedisRepository<RefreshToken> {
    @Autowired
    public RefreshTokenRedisDao(RedisTemplate redisTemplate) {
        this.prefix = REFRESH_TOKEN + ":";
        this.redisTemplate = redisTemplate;
    }
}
