package likelion.univ.project.dto.request;

import likelion.univ.domain.user.entity.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectMemberRequestDto {

    private Long userId;
    private Part part;
}
