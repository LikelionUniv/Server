package likelion.univ.auth.dto.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountTokenDto {

    @JsonInclude(NON_NULL)
    private String accessToken;
    @JsonInclude(NON_NULL)
    private String refreshToken;
    private Boolean isRegistered;

    public static AccountTokenDto of(String accessToken, String refreshToken) {
        return AccountTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isRegistered(true)
                .build();
    }

    public static AccountTokenDto notRegistered() {
        return AccountTokenDto.builder()
                .accessToken(null)
                .refreshToken(null)
                .isRegistered(false)
                .build();
    }
}
