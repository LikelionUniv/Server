package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.EmailContentsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.email.EmailContent;
import likelion.univ.email.EmailSender;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmailAlarmUsecase {

    private final EmailSender emailSender;

    public void execute(EmailContentsDto emailContentsDto) {;

        EmailContent emailContent = EmailContent.builder()
                .subject(emailContentsDto.getSubject())
                .sender(emailContentsDto.getSender())
                .contents(emailContentsDto.getContent())
                .receivers(emailContentsDto.getEmails())
                .build();

        emailSender.send(emailContent);

        // sms 보내려면 user 도메인에 전화번호 필요
    }
}
