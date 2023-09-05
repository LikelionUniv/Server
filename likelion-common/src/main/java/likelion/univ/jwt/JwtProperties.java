package likelion.univ.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProperties {
    private String secretKey;
    private Long accessTokenExp;
    private Long refreshTokenExp;
}
