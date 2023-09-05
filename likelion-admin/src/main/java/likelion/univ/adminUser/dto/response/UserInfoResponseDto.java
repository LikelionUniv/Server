package likelion.univ.adminUser.dto.response;

import likelion.univ.domain.user.entity.Part;
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

    public static UserInfoResponseDto of(User user){
        return UserInfoResponseDto.builder()
                .name(user.getProfile().getName())
                .id(user.getId())
                .major(user.getUniversityInfo().getMajor())
                .part(user.getProfile().getPart())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .email(user.getAuthInfo().getEmail())
                .build();
    }
}