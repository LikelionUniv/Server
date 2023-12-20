package likelion.univ.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cloud.azure.communication")
@ConstructorBinding
public class AzureCommunicationProperties {

    private final String endpoint;
    private final String credential;
    private final String sender;
}
