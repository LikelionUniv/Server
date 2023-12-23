package likelion.univ.security;

import likelion.univ.environment.ProfileProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final ProfileProcessor profileProcessor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();

        allowedOriginPatterns.add("https://likelionuniv.com");
        allowedOriginPatterns.add("https://likelion.university");
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("http://localhost:8080");
        allowedOriginPatterns.add("https://stag.likelionuniv.com");
        allowedOriginPatterns.add("https://stag.likelion.university");
        allowedOriginPatterns.add("https://likelionunivtemp.vercel.app");
//        if(profileProcessor.isStag()){
//            allowedOriginPatterns.add("http://localhost:3000");
//            allowedOriginPatterns.add("http://localhost:8080");
//            allowedOriginPatterns.add("https://stag.likelionuniv.com");
//            allowedOriginPatterns.add("https://stag.likelion.university");
//            allowedOriginPatterns.add("https://likelionunivtemp.vercel.app");
//        }

        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedOriginPatterns(patterns)
                .allowCredentials(true);
    }
}
