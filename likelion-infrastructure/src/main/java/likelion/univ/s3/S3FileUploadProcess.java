package likelion.univ.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import likelion.univ.annotation.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Profile("!test")
@Processor
@RequiredArgsConstructor
public class S3FileUploadProcess {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    /**
     * @param fileUri
     * @param file
     * @return fileUrl
     */
    public String execute(String fileUri, File file) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(s3Properties.getBucket(), fileUri, file);
            amazonS3.putObject(putObjectRequest);

            String fileUrl = amazonS3.getUrl(s3Properties.getBucket(), fileUri).toString();
            return fileUrl;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
}