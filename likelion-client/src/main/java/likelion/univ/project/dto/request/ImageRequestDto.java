package likelion.univ.project.dto.request;

import likelion.univ.domain.project.entity.Image;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class ImageRequestDto{

    private String name;
    private String saved;

    @Builder
    public ImageRequestDto(Image image){
        this.name = image.getName();
        this.saved =image.getSaved();
    }

}
