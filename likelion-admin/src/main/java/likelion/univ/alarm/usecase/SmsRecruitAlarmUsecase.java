package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.AlarmContentsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.sms.SmsContent;
import likelion.univ.sms.SmsSender;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class SmsRecruitAlarmUsecase implements AlarmUsecase {

    private final RecruitQueryService recruitQueryService;
    private final SmsSender smsSender;

    public void execute(AlarmContentsDto alarmContentsDto) {
        List<Recruit> recruits = recruitQueryService.queryAllByUniversityName(alarmContentsDto.getUniversityName());

        List<String> phones = recruits.stream()
                .map(Recruit::getPhoneNumber)
                .collect(Collectors.toList());

        SmsContent smsContent = SmsContent.builder()
                .contents(alarmContentsDto.getContent())
                .receivers(phones)
                .build();
        smsSender.send(smsContent);
    }
}
