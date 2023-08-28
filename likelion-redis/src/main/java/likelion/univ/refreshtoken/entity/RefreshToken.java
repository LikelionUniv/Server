package likelion.univ.refreshtoken.entity;


import likelion.univ.common.constant.RedisKey;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.GeneratedValue;

@Getter
@AllArgsConstructor
@RedisHash(value = RedisKey.REFRESH_TOKEN, timeToLive = RedisKey.REFRESH_TOKEN_EXPIRE_SEC)
@Builder
public class RefreshToken {

    @Id
    private Long userId;
    private String token;
}
