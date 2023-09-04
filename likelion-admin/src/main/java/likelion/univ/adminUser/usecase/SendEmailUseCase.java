package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class SendEmailUseCase {
    private final UserAdaptor userAdaptor;
    private final JavaMailSender mailSender;



    public void excute(SendMailRequestDto sendMailRequestDto) throws MessagingException, IOException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

        helper.setSubject(sendMailRequestDto.getTitle());
        helper.setText(sendMailRequestDto.getBody());

        //로컬 첨부 파일 설정
        if(sendMailRequestDto.getFiles()!=null) {
            for(MultipartFile multipartFile : sendMailRequestDto.getFiles()) {
                helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
            }
        }
        helper.setTo(sendMailRequestDto.getToAddress());
        mailSender.send(mail);
    }



}
