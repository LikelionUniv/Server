package likelion.univ.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cloud.aws.s3")
@ConstructorBinding
public class S3Properties {
    
    private String bucket;
    private String accessKey;
    private String secretKey;
    private String region;
    private String accessDomain;
}
