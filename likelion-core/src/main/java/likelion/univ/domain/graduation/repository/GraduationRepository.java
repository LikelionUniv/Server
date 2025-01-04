package likelion.univ.domain.graduation.repository;

import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.graduation.exception.GraduationNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GraduationRepository extends JpaRepository<Graduation, Long> {
    List<Graduation> findAllByOrdinalAndUserIdIn(Long ordinal, List<Long> userIds);

    Optional<Graduation> findTopByUserIdAndOrdinal(Long userId, Long ordinal);

    default Graduation getById(Long id) {
        return findById(id).orElseThrow(GraduationNotFoundException::new);
    }
}
