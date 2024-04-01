package likelion.univ.user.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSearchResultDto {
    private Long userId;
    private String name;
    private String profileImage;
    private String universityName;
    private Long ordinal;
    private String part;

    public static UserSearchResultDto of(User user) {
        return UserSearchResultDto.builder()
                .userId(user.getId())
                .name(user.getProfile().getName())
                .profileImage(user.getProfile().getProfileImage())
                .universityName(user.getUniversityInfo().getUniversity().getName())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .part(user.getProfile().getPart().getValue())
                .build();
    }

}
