package likelion.univ.config.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FilterConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;



    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        builder.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }
}