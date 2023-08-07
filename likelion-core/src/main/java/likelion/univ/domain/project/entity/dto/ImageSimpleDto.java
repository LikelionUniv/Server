package likelion.univ.domain.project.entity.dto;

import likelion.univ.domain.project.entity.Image;
import lombok.Data;

@Data
public class ImageSimpleDto {

    private String name;
    private String saved;

    public ImageSimpleDto() {
    }

    public ImageSimpleDto(Image image) {
        this.name = image.getName();
        this.saved = image.getSaved();
    }
}
