package likelion.univ.auth.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountUserInfoDto {
    private Long userId;
    private String profileImage;
    private String name;

    public static AccountUserInfoDto of(User user){
        return AccountUserInfoDto.builder()
                .userId(user.getId())
                .profileImage(user.getProfile().getProfileImage())
                .name(user.getProfile().getName())
                .build();
    }

}
