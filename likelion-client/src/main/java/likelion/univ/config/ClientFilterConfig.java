package likelion.univ.config;

import likelion.univ.security.filter.FilterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
    private final FilterProcessor filterProcessor;
    @Override
    public void configure(HttpSecurity builder) throws Exception  {
        filterProcessor.common(builder);
    }
}