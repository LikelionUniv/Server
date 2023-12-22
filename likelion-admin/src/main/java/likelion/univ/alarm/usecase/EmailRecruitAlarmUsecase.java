package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.RecruitEmailAlarmDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class EmailRecruitAlarmUsecase {

    private final EmailSender emailSender;

    public void execute(RecruitEmailAlarmDto recruitEmailAlarmDto) {
        EmailContent emailContent = EmailContent.builder()
                .subject(recruitEmailAlarmDto.getSubject())
                .contents(recruitEmailAlarmDto.getContents())
                .receivers(recruitEmailAlarmDto.getReceivers())
                .build();

        emailSender.send(emailContent);
    }
}
