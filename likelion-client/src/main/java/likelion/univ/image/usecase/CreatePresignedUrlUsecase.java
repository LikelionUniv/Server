package likelion.univ.image.usecase;

import java.util.UUID;
import likelion.univ.annotation.UseCase;
import likelion.univ.image.dto.response.ImageUrlResponseDto;
import likelion.univ.s3.GeneratePresignedUrlProcessor;
import likelion.univ.s3.S3Properties;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePresignedUrlUsecase {

    private final GeneratePresignedUrlProcessor generatePresignedUrlProcessor;
    private final S3Properties s3Properties;

    public ImageUrlResponseDto execute(String prefix, Long id, String fileNameExtension) {
        String fileName = createFileName(prefix, id, fileNameExtension);
        String presignedUrl = generatePresignedUrlProcessor.execute(fileName);
        String imageUrl = s3Properties.getAccessDomain() + fileName;
        return ImageUrlResponseDto.of(presignedUrl, imageUrl, fileName);
    }

    private String createFileName(String prefix, Long id, String fileNameExtension) {
        String uuid = UUID.randomUUID().toString();
        return "image/" + prefix + "/" + id.toString() + "/" + uuid + "." + fileNameExtension;
    }
}