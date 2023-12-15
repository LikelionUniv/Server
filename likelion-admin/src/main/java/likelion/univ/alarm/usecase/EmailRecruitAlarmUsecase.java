package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.RecruitEmailAlarmDto;
import likelion.univ.alarm.emailsender.EmailSender;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.EmailContent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class EmailRecruitAlarmUsecase {

    private final RecruitQueryService recruitQueryService;
    private final EmailSender emailSender;

    public void execute(RecruitEmailAlarmDto recruitEmailAlarmDto) {
        List<Recruit> recruits = recruitQueryService
                .queryAllByUniversityNameAndGeneration(recruitEmailAlarmDto.getUniversityName(),
                        recruitEmailAlarmDto.getGeneration());

        List<String> emails = recruits.stream()
                .map(Recruit::getEmail)
                .collect(Collectors.toList());

        EmailContent emailContent = EmailContent.builder()
                .subject(recruitEmailAlarmDto.getSubject())
                .contents(recruitEmailAlarmDto.getContents())
                .receivers(emails)
                .build();

        emailSender.send(emailContent);
    }
}
