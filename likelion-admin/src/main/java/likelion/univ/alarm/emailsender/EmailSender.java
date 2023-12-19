package likelion.univ.alarm.emailsender;

import likelion.univ.email.EmailContent;

public interface EmailSender {

    void send(EmailContent emailContent);
}
