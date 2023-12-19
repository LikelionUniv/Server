package likelion.univ.config;

import likelion.univ.alarm.emailsender.AzureEmailSender;
import likelion.univ.alarm.emailsender.EmailSender;
import likelion.univ.email.AzureEmailProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConfig {

    private final AzureEmailProcessor processor;

    @Bean
    public EmailSender emailSender() {
        return new AzureEmailSender(processor);
    }
}
