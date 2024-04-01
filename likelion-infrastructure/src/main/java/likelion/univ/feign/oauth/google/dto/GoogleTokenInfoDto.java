package likelion.univ.feign.oauth.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GoogleTokenInfoDto {
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private Long expiresIn;
    private String tokenType;
}

