package likelion.univ.alarm.emailsender;

import likelion.univ.email.AzureEmailProcessor;
import likelion.univ.email.EmailContent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AzureEmailSender implements EmailSender {

    private final AzureEmailProcessor emailProcessor;

    @Override
    public void send(EmailContent emailContent) {
        emailProcessor.send(emailContent);
    }
}
