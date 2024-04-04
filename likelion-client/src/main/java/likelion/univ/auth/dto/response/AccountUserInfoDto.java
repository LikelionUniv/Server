package likelion.univ.auth.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountUserInfoDto {

    private Long userId;
    private String profileImage;
    private String universityName;
    private String role;
    private String name;

    public static AccountUserInfoDto of(User user, String universityName) {
        return AccountUserInfoDto.builder()
                .userId(user.getId())
                .profileImage(user.getProfile().getProfileImage())
                .universityName(universityName)
                .role(user.getAuthInfo().getRole().toString())
                .name(user.getProfile().getName())
                .build();
    }
}
