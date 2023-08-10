package likelion.univ.adminUser.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfoResponseDto {
    private String name;

    public static UserInfoResponseDto of(User user){
        return UserInfoResponseDto.builder()
                .name(user.getProfile().getName())
                .build();
    }
}