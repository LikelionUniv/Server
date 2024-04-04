package likelion.univ.example.dto.response;

import likelion.univ.domain.example.entity.Example;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExampleInfoResponseDto {

    private String body;

    public static ExampleInfoResponseDto of(Example example) {
        return ExampleInfoResponseDto.builder()
                .body(example.getBody())
                .build();
    }
}
