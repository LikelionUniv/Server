package likelion.univ.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "google")
@ConstructorBinding
public class GoogleProperties {
    
    private String iss;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String redirectUrlForLocal;
    private String apiKey;
}
