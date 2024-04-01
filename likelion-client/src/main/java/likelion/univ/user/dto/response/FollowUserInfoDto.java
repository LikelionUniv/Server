package likelion.univ.user.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowUserInfoDto {

    private Long userId;
    private String name;
    private String profileImage;
    private Long ordinal;
    private String part;
    private Boolean isFollowed;

    public static FollowUserInfoDto of(User user, Boolean isFollowed) {
        return FollowUserInfoDto.builder()
                .userId(user.getId())
                .name(user.getProfile().getName())
                .profileImage(user.getProfile().getProfileImage())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .part(user.getProfile().getPart().toString())
                .isFollowed(isFollowed)
                .build();
    }
}
