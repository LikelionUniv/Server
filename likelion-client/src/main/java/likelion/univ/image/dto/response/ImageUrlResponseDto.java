package likelion.univ.image.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageUrlResponseDto {

    private String presignedUrl;
    private String imageUrl;
    private String fileName;

    public static ImageUrlResponseDto of(String presignedUrl, String imageUrl, String fileName) {
        return ImageUrlResponseDto.builder()
                .presignedUrl(presignedUrl)
                .imageUrl(imageUrl)
                .fileName(fileName)
                .build();
    }
}
