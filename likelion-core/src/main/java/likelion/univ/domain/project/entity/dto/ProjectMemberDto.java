package likelion.univ.domain.project.entity.dto;

import likelion.univ.domain.user.entity.User;
import lombok.Data;

@Data
public class ProjectMemberDto {

    private Long userID;
    private String name;

    public ProjectMemberDto() {
    }

    public ProjectMemberDto(Long id, String name) {
        this.userID = id;
        this.name = name;
    }
}
