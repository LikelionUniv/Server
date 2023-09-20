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
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import org.springframework.http.HttpHeaders;
>>>>>>> 90c65fd9c7c40efcf69d0e93fa520fc3d0b32ed4

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
                .title("Likelion-Univ Admin API")
                .version("v1")
                .description("Likelion-Univ Admin API문서입니다.")
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

    private io.swagger.v3.oas.models.security.SecurityScheme securityScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
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