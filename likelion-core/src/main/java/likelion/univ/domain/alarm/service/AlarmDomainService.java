package likelion.univ.domain.alarm.service;

import likelion.univ.domain.alarm.adaptor.AlarmAdaptor;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.entity.AlarmType;
import likelion.univ.domain.alarm.entity.SendStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static likelion.univ.domain.alarm.entity.SendStatus.NOT_SENT;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmDomainService {
    private final AlarmAdaptor alarmAdaptor;

    @Transactional
    public Alarm createAlarm(Alarm alarm){
        return alarmAdaptor.save(alarm);
    }
}
