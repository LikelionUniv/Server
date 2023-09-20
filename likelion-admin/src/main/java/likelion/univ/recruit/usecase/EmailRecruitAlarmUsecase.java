package likelion.univ.recruit.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.EmailContent;
import likelion.univ.email.EmailSender;
import likelion.univ.recruit.dto.RecruitAlarmContentDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class EmailRecruitAlarmUsecase implements RecruitAlarmUsecase {

    private final RecruitQueryService recruitQueryService;
    private final EmailSender emailSender;

    public void execute(RecruitAlarmContentDto recruitAlarmContentDto, Long universityId) {
        List<Recruit> recruits = recruitQueryService.queryAllByUniversity(universityId);
        List<String> emails = recruits.stream()
                .map(Recruit::getEmail)
                .collect(Collectors.toList());

        EmailContent emailContent = EmailContent.builder()
                .subject(recruitAlarmContentDto.getSubject())
                .sender(recruitAlarmContentDto.getSender())
                .contents(recruitAlarmContentDto.getContents())
                .receivers(emails)
                .build();

        emailSender.send(emailContent);
    }
}
