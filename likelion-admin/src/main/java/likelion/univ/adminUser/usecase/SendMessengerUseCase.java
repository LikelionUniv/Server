package likelion.univ.adminUser.usecase;

import com.google.gson.JsonObject;
import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.adminUser.dto.request.SendMsgRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class SendMessengerUseCase {
    private final UserAdaptor userAdaptor;
    private final JavaMailSender mailSender;
    private DefaultMessageService messageService;

    @Value("${spring.coolsms.devHee.apikey}")
    private String api_key;
    @Value("${spring.coolsms.devHee.apisecret}")
    private String api_secret;
    @Value("${spring.coolsms.devHee.fromNum}")
    private String fromNum;


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

    public void sendMsg(SendMsgRequestDto sendMsgRequestDto) throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        messageService = NurigoApp.INSTANCE.initialize(api_key,api_secret, "https://api.coolsms.co.kr");
        ArrayList<Message> messageList = new ArrayList<>();

        for(String toNum : sendMsgRequestDto.getPhoneNum()){
            Message message = new Message();
            message.setFrom(fromNum);
            message.setTo(toNum);
            message.setText(sendMsgRequestDto.getMsg());
            messageList.add(message);
        }
        MultipleDetailMessageSentResponse response = this.messageService.send(messageList,false, false);



    }

}
