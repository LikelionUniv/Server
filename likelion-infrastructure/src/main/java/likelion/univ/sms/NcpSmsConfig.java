package likelion.univ.sms;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/*
* serviceId의 ":" 자동 인코딩 방지
* */
public class NcpSmsConfig {
    @Bean
    public RequestInterceptor basicAuthRequestInterceptor(){
        return new ColonInterceptor();
    }

    public static class ColonInterceptor implements RequestInterceptor{
        @Override
        public void apply(RequestTemplate template) {
            template.uri(template.path().replaceAll("%3A",":"));
        }
    }
}
