package likelion.univ.domain.alarm.repository;

import likelion.univ.domain.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    boolean existsByOrdinalAndEmail(Long ordinal, String email);
}
