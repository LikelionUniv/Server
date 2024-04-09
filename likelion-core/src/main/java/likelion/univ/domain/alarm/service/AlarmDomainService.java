package likelion.univ.domain.alarm.service;

import java.util.List;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmDomainService {

    private final AlarmRepository alarmRepository;

    @Transactional
    public Alarm createAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public List<Alarm> findAllByOrdinal(Long ordinal) {
        return alarmRepository.findAllByOrdinal(ordinal);
    }

    public void deleteById(Long id) {
        alarmRepository.deleteById(id);
    }
}
