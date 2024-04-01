package likelion.univ.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import java.net.URL;
import java.util.Date;
import likelion.univ.annotation.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Processor
@RequiredArgsConstructor
public class GeneratePresignedUrlProcessor {
    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public String execute(String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(s3Properties.getBucket(), fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString()
        );
        URL presignedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return presignedUrl.toString();
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
