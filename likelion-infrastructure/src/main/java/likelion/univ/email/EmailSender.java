package likelion.univ.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@Component
public class EmailSender {

    private final String SENDER_EMAIL;
    private final String SENDER_PASSWORD;
    private Properties prop;
    private Session session;

    public EmailSender(
            @Value("${alarm.email.sender-email}") String email,
            @Value("${alarm.email.sender-password}") String password) {
        this.SENDER_EMAIL = email;
        this.SENDER_PASSWORD = password;
    }

    public void send(EmailContent emailContent) {
        emailSetUp();
        MimeMessage message= new MimeMessage(session);
        try {
            // 메일 제목
            message.setSubject(emailContent.getSubject());

            // 보내는 사람 (보내는 email 주소, 보내는 email 주소 이름, charset)
            // ex. (example@likelion.org, "멋쟁이사자처럼 대학", charset)
            message.setFrom(new InternetAddress(SENDER_EMAIL, emailContent.getSender(), "UTF-8"));

            // 받는 사람
            message.addRecipients(Message.RecipientType.TO, convertEmailAddress(emailContent.getReceivers()));

            // 메일 본문
            message.setContent(emailContent.getContents(), "text/html;charset=UTF-8"); // 내용
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void emailSetUp() {
        this.prop = gmailSetup();
        this.session = createEmailSession(prop);
    }

    private Properties gmailSetup() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return prop;
    }

    private Session createEmailSession(Properties prop) {
        return Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });
    }

    private InternetAddress[] convertEmailAddress(List<String> receivers) {
        return receivers.stream()
                .map(receiver -> {
                    try {
                        return new InternetAddress(receiver);
                    } catch (AddressException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(InternetAddress[]::new);
    }
}
