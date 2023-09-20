package likelion.univ.recruit.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.email.EmailContent;
import likelion.univ.email.EmailSender;
import likelion.univ.recruit.dto.RecruitAlarmContent;
import likelion.univ.sms.SmsContent;
import likelion.univ.sms.SmsSender;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class SmsRecruitAlarmUsecase implements RecruitAlarmUsecase {

    private final RecruitQueryService recruitQueryService;
    private final SmsSender smsSender;

    public void execute(RecruitAlarmContent recruitAlarmContent, Long universityId) {
        List<Recruit> recruits = recruitQueryService.queryAllByUniversity(universityId);

        List<String> phones = recruits.stream()
                .map(Recruit::getPhoneNumber)
                .collect(Collectors.toList());

        SmsContent smsContent = SmsContent.builder()
                .contents(recruitAlarmContent.getContents())
                .receivers(phones)
                .build();
        smsSender.send(smsContent);
    }
}
