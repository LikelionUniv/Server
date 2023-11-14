package likelion.univ.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


public abstract class BaseRedisRepository<T> {
    protected RedisTemplate<String, T> redisTemplate;
    protected String prefix;
    protected Long ttl;

    public void save(Long id, T data) {
        redisTemplate.opsForValue().set(generateKeyfromId(id), data, ttl, TimeUnit.SECONDS);
    }

    public Optional<T> findById(Long id) {
        try{
            return Optional.of(redisTemplate.opsForValue().get(generateKeyfromId(id)));
        }catch (NullPointerException e){
            return Optional.empty();
        }
    }
    public Boolean delete(Long id) {
        return redisTemplate.delete(generateKeyfromId(id));
    }

    public String generateKeyfromId(Long id){
        return prefix + id.toString();
    }

}
