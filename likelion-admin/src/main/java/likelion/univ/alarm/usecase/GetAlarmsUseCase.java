package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.GetAlarmsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.service.AlarmDomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAlarmsUseCase {

    private final AlarmDomainService alarmDomainService;

    public GetAlarmsDto execute(Long ordinal) {
        List<Alarm> alarms = alarmDomainService.findAllByOrdinal(ordinal);
        return GetAlarmsDto.of(alarms);
    }
}
