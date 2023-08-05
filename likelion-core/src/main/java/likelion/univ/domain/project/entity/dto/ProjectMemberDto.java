package likelion.univ.domain.project.entity.dto;

import likelion.univ.domain.user.entity.User;
import lombok.Data;

@Data
public class ProjectMemberDto {

    private Long userID;
    private String name;

    public ProjectMemberDto(User user) {
        this.userID = user.getId();
        this.name = user.getProfile().getName();
    }
}
