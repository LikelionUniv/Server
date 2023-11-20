package likelion.univ.refreshtoken.entity;


import likelion.univ.common.constant.RedisKey;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.GeneratedValue;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    private Long userId;
    private String token;
}
