package likelion.univ.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("http://localhost:8080");
        allowedOriginPatterns.add("https://likelionuniv.com");
        allowedOriginPatterns.add("https://stag.likelionuniv.com");
        allowedOriginPatterns.add("http://localhost:8080"); // local test

        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedOriginPatterns(patterns)
                .allowCredentials(true);
    }
}
