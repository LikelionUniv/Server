package likelion.univ.project.dto.response;

import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String university;
    private Long ordinal;
    private String part;

    public static UserResponseDto of(User user, University university) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getProfile().getName())
                .university(university.getName())
                .ordinal(user.getUniversityInfo().getOrdinal())
                .part(user.getProfile().getPart().getValue())
                .build();
    }
}
