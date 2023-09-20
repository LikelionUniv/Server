package likelion.univ.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("Authorization(accessToken)", securityScheme()))
                // 인증 전역설정
                .addSecurityItem(securityItem())
                .info(info());
    }
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
    private Info info(){
        return new Info()
                .title("Likelion-Univ Client API")
                .version("v1")
                .description("Likelion-Univ Client API문서입니다.")
                .license(license())
                .contact(new Contact() // 연락처
                        .name("Likelion-Univ")
                        .email("tmfrk0426@gmail.com"));
    }
    private License license(){
        License license = new License();
        license.setUrl("https://github.com/LikelionUniv/LikelionUniv-Server");
        license.setName("멋쟁이 사자처럼");
        return license;
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }

    // Security 요청 설정
    private SecurityRequirement securityItem(){
        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList("Authorization");
        return securityItem;
    }
}