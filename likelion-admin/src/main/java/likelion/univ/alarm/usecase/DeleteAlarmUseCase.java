package likelion.univ.alarm.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.alarm.service.AlarmDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteAlarmUseCase {

    private final AlarmDomainService alarmDomainService;

    public void execute(Long id) {
        alarmDomainService.deleteById(id);
    }
}
