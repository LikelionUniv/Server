package likelion.univ.security.filter;

import likelion.univ.annotation.Processor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Processor
@RequiredArgsConstructor
public class FilterProcessor extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public HttpSecurity common(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        builder.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        return builder;
    }


}