package likelion.univ.project.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectMemberResponseDto {

//    private Long userID;
    private String name;

    public static ProjectMemberResponseDto of(String name) {
        return ProjectMemberResponseDto.builder()
                .name(name)
                .build();
    }
}
