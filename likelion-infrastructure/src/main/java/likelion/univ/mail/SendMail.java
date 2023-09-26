package likelion.univ.mail;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SendMail {
    private final JavaMailSender mailSender;


    @SneakyThrows
    public void sendMail(String title, String body, List<MultipartFile> files, String[] toAddress) throws IOException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

        helper.setSubject(title);
        helper.setText(body);

        //로컬 첨부 파일 설정
        if(files!=null) {
            for(MultipartFile multipartFile : files) {
                helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
            }
        }
        helper.setTo(toAddress);
        mailSender.send(mail);
    }

}
