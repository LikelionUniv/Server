package likelion.univ.security.filter;

import likelion.univ.annotation.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Processor
@RequiredArgsConstructor
public class FilterProcessor {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public void common(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        builder.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }
}