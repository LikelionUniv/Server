package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.SendEmailDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SendEmailUseCase {

    private final EmailSender emailSender;

    public void execute(SendEmailDto sendEmailDto, List<MultipartFile> attachments) {
        EmailContent emailContent = EmailContent.builder()
                .subject(sendEmailDto.getSubject())
                .contents(sendEmailDto.getContents())
                .receivers(sendEmailDto.getReceivers())
                .attachments(attachments)
                .build();

        emailSender.send(emailContent);
    }
}
