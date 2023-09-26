package likelion.univ.project.dto.response;

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
