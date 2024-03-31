package likelion.univ.common.base;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;


public abstract class BaseRedisRepository<T> {

    protected RedisTemplate<String, T> redisTemplate;
    protected String prefix;
    protected Long ttl;

    public void save(Long id, T data) {
        redisTemplate.opsForValue().set(generateKeyFromId(id), data, ttl, TimeUnit.SECONDS);
    }

    public Optional<T> findById(Long id) {
        try {
            return Optional.of(redisTemplate.opsForValue().get(generateKeyFromId(id)));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public Boolean delete(Long id) {
        return redisTemplate.delete(generateKeyFromId(id));
    }

    public String generateKeyFromId(Long id) {
        return prefix + id.toString();
    }
}
