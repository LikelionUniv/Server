package likelion.univ.config;

import likelion.univ.security.AccessProcessor;
import likelion.univ.security.filter.FilterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static likelion.univ.constant.StaticValue.SwaggerUrlPatterns;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final FilterProcessor filterProcessor;
    private final AccessProcessor accessProcessor;
    private final CustomAuthenticationEntryPoint entryPoint;


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
        filterProcessor.common(http);
        http.exceptionHandling().authenticationEntryPoint(entryPoint); // 로그인 x 유저에 대한 Custom ErrorResponse 제공
        http.authorizeRequests().expressionHandler(accessProcessor.expressionHandler());

        http
                .authorizeRequests()
                .antMatchers(SwaggerUrlPatterns).permitAll()

                // community - 2024.01.29 기준 GUEST 유저는 사용 불능하도록 권한 검사 설정
//                .antMatchers(HttpMethod.GET, "/v1/community/**").authenticated() // 추후 조회에 대하여는 로그인만 요청할 계획이라 주석으로 냅둠
                .antMatchers("/v1/community/**").hasRole("USER")


//                .anyRequest().authenticated();
                .anyRequest().permitAll(); //임시

        return http.build();
    }
}
