package likelion.univ.security;

import likelion.univ.annotation.Processor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;

@Processor
public class CorsProcessor{

    public void common(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("https://likelionuniv.com");
        allowedOriginPatterns.add("https://stag.likelionuniv.com");

        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedOriginPatterns(patterns)
                .allowCredentials(true);
    }
}
