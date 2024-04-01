package likelion.univ.user.dto.response;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileDetailsDto {

    private Long id;
    private String profileImage;
    private String name;
    private String role;
    private String phoneNum;
    private String universityName;
    private String major;
    private Long ordinal;
    private String part;
    private Long followerNum;
    private Long followingNum;
    private String introduction;
    private Boolean isMine;

    public static ProfileDetailsDto of(User user, Boolean isMine, Long followerNum, Long followingNum) {
        return ProfileDetailsDto.builder()
                .id(user.getId())
                .profileImage(user.getProfile().getProfileImage())
                .name(user.getProfile().getName())
                .role(user.getAuthInfo().getRole().getValue())
                .phoneNum(user.getAuthInfo().getPhoneNumber())
                .universityName(user.getUniversityInfo().getUniversity().getName())
                .major(user.getUniversityInfo().getMajor())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .part(user.getProfile().getPart().toString())
                .followerNum(followerNum)
                .followingNum(followingNum)
                .introduction(user.getProfile().getIntroduction())
                .isMine(isMine)
                .build();
    }
}
