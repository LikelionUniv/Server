package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.user.entity.Part;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectMemberResponseDto {

    private Long userId;
    private String name;
    private Part part;

    public static ProjectMemberResponseDto of(Long userId, String name, Part part) {
        return ProjectMemberResponseDto.builder()
                .userId(userId)
                .name(name)
                .part(part)
                .build();
    }
    public static ProjectMemberResponseDto of(ProjectMember projectMember) {
        return ProjectMemberResponseDto.builder()
                .userId(projectMember.getUser().getId())
                .name(projectMember.getUser().getProfile().getName())
                .part(projectMember.getPart())
                .build();
    }
}
