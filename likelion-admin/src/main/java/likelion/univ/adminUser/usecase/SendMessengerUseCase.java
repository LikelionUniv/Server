package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.FileItem;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class SendMessengerUseCase {
    private final UserAdaptor userAdaptor;
    private final JavaMailSender mailSender;
    public void sendEmail(SendMailRequestDto sendMailRequestDto) throws MessagingException, IOException {
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
