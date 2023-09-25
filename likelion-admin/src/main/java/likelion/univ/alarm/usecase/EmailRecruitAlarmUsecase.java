package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.AlarmContentsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.EmailContent;
import likelion.univ.email.EmailSender;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class EmailRecruitAlarmUsecase implements AlarmUsecase {

    private final RecruitQueryService recruitQueryService;
    private final EmailSender emailSender;

    public void execute(AlarmContentsDto alarmContentsDto) {
        List<Recruit> recruits = recruitQueryService.queryAllByUniversityName(alarmContentsDto.getUniversityName());
        List<String> emails = recruits.stream()
                .map(Recruit::getEmail)
                .collect(Collectors.toList());

        EmailContent emailContent = EmailContent.builder()
                .subject(alarmContentsDto.getSubject())
                .sender(alarmContentsDto.getSender())
                .contents(alarmContentsDto.getContent())
                .receivers(emails)
                .build();

        emailSender.send(emailContent);
    }
}
