package likelion.univ.domain.alarm.service;

import likelion.univ.domain.alarm.adaptor.AlarmAdaptor;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmDomainService {
    private final AlarmAdaptor alarmAdaptor;
    private final AlarmRepository alarmRepository;

    @Transactional
    public Alarm createAlarm(Alarm alarm){
        return alarmAdaptor.save(alarm);
    }

    public List<Alarm> findAllByOrdinal(Long ordinal) {
        return alarmRepository.findAllByOrdinal(ordinal);
    }

    public void deleteById(Long id) {
        alarmRepository.deleteById(id);
    }
}
