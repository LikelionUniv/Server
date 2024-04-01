package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.request.AlarmRegisterRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.alarm.adaptor.AlarmAdaptor;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.service.AlarmDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterAlarmUseCase {
    private final AlarmAdaptor alarmAdaptor;
    private final AlarmDomainService alarmDomainService;

    public void execute(Long ordinal, AlarmRegisterRequestDto alarmRegisterRequestDto) {
        alarmAdaptor.existsByOrdinalAndEmailAndAlarmType(ordinal, alarmRegisterRequestDto.getEmail());
        Alarm newAlarm = AlarmRegisterRequestDto.toEntity(ordinal, alarmRegisterRequestDto);
        alarmDomainService.createAlarm(newAlarm);
    }
}