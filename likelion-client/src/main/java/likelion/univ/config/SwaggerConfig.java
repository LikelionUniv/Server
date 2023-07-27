package likelion.univ.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "likelionuniv Client API",
                description = "likelionuniv 클라이언트 모듈 API 명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    private final ObjectMapper om;
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("likelionuniv")
                .pathsToMatch("/**")
                .build();
    }

}
