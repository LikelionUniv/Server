package likelion.univ.adminUser.dto.response;

import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String email;
    private String major;
    private Part part;
    private Long ordinal;
    private Role role;


    public static UserInfoResponseDto of(User user){
        return new UserInfoResponseDtoBuilder()
                .id(user.getId())
                .name(user.getProfile().getName())
                .major(user.getUniversityInfo().getMajor())
                .part(user.getProfile().getPart())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .email(user.getAuthInfo().getEmail())
                .role(user.getAuthInfo().getRole())
                .build();
    }
}