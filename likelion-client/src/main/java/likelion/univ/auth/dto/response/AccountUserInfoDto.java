package likelion.univ.auth.dto.response;

import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountUserInfoDto {

    private Long userId;
    private String profileImage;
    private Long universityId;
    private String universityName;
    private String role;
    private String email;
    private String name;

    public static AccountUserInfoDto of(User user, University university) {
        return AccountUserInfoDto.builder()
                .userId(user.getId())
                .profileImage(user.getProfile().getProfileImage())
                .universityId(university != null ? university.getId() : null)
                .universityName(university != null ? university.getName() : null)
                .role(user.getAuthInfo().getRole().toString())
                .email(user.getAuthInfo().getEmail())
                .name(user.getProfile().getName())
                .build();
    }
}
