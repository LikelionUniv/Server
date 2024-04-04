package likelion.univ.alarm.usecase;

import java.util.List;
import likelion.univ.alarm.dto.GetAlarmsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.service.AlarmDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetAlarmsUsecase {

    private final AlarmDomainService alarmDomainService;

    public GetAlarmsDto execute(Long ordinal) {
        List<Alarm> alarms = alarmDomainService.findAllByOrdinal(ordinal);
        return GetAlarmsDto.of(alarms);
    }
}
