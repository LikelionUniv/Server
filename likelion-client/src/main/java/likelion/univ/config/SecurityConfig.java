package likelion.univ.config;

import likelion.univ.security.AccessProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static likelion.univ.constant.StaticValue.SwaggerUrlPatterns;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final ClientFilterConfig clientFilterConfig;
    private final AccessProcessor accessProcessor;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic().disable().cors();// cors 적용
        http.csrf().disable();
        http.formLogin().disable();
        http.sessionManagement( ).sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT이용으로 세션 이용 x
        http.apply(clientFilterConfig);
        http.authorizeRequests().expressionHandler(accessProcessor.expressionHandler());

        http
                .authorizeRequests()
                .antMatchers(SwaggerUrlPatterns)
                .permitAll()
//                .antMatchers("/v1/**").hasRole(ROLE_USER)
//                .anyRequest().authenticated();
                .anyRequest().permitAll(); //임시
        return http.build();
    }
}
