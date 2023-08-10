package likelion.univ.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountTokenDto {
    private String accessToken;
    private String refreshToken;

    public static AccountTokenDto of(String accessToken,String refreshToken){
        return AccountTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
