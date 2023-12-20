package likelion.univ.project.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectMemberResponseDto {

    private Long userId;
    private String name;

    public static ProjectMemberResponseDto of(Long userId, String name) {
        return ProjectMemberResponseDto.builder()
                .userId(userId)
                .name(name)
                .build();
    }
}
