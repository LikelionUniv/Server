package likelion.univ.project.dto.request;

import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class ProjectMemberRequestDto {
    private User user;

    @Builder
    public ProjectMemberRequestDto(ProjectMember projectMember){
        this.user = projectMember.getUser();
    }

}
