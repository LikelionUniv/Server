package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.AlarmContentsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.email.EmailContent;
import likelion.univ.email.EmailSender;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmailAlarmUsecase implements AlarmUsecase {

    private final EmailSender emailSender;

    public void execute(AlarmContentsDto alarmContentsDto) {
        EmailContent emailContent = EmailContent.builder()
                .subject(alarmContentsDto.getSubject())
                .sender(alarmContentsDto.getSender())
                .contents(alarmContentsDto.getContent())
                .receivers(alarmContentsDto.getEmails())
                .build();

        emailSender.send(emailContent);
    }
}
