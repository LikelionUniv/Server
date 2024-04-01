package likelion.univ.university.dto.response;

import lombok.Builder;
import lombok.Data;

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
