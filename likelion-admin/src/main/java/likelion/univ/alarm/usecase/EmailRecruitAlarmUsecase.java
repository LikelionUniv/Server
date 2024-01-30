package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.RecruitEmailAlarmDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class EmailRecruitAlarmUsecase {

    private final EmailSender emailSender;

    public void execute(RecruitEmailAlarmDto recruitEmailAlarmDto, List<MultipartFile> attachments) {
        EmailContent emailContent = EmailContent.builder()
                .subject(recruitEmailAlarmDto.getSubject())
                .contents(recruitEmailAlarmDto.getContents())
                .receivers(recruitEmailAlarmDto.getReceivers())
                .attachments(attachments)
                .build();

        emailSender.send(emailContent);
    }
}
