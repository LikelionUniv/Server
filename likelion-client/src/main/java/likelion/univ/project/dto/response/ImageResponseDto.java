package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponseDto {

    private String name;
    private String saved;

    public static ImageResponseDto of(Image image) {
        return ImageResponseDto.builder()
                .name(image.getName())
                .saved(image.getSaved())
                .build();
    }
}
