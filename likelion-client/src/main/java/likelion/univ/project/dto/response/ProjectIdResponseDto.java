package likelion.univ.project.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectIdResponseDto {

    private Long id;

    public static ProjectIdResponseDto of(Long id) {
        return ProjectIdResponseDto.builder()
                .id(id)
                .build();
    }
}
