package likelion.univ.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoFromIdToken {
    private String sub;
    private String nickname;
    private String email;
}
