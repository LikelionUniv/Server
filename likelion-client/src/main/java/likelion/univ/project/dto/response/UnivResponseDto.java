package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UnivResponseDto {

    private String name;

    public static UnivResponseDto of(String name) {
        return UnivResponseDto.builder()
                .name(name)
                .build();
    }
}
