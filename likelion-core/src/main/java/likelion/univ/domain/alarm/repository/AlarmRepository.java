package likelion.univ.domain.alarm.repository;

import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.entity.AlarmType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    boolean existsByOrdinalAndEmailAndAlarmType(Long ordinal, String email, AlarmType alarmType);
}
