package likelion.univ.domain.university.repository;

import java.util.List;
import java.util.Optional;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.entity.UniversityStatus;
import likelion.univ.domain.university.exception.UniversityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {

    default University getById(Long id) {
        return findById(id).orElseThrow(UniversityNotFoundException::new);
    }

    default University getByName(String name) {
        return findByName(name).orElseThrow(UniversityNotFoundException::new);
    }

    Optional<University> findByName(String name);

    default List<University> findByLocationAndStatusIsActive(String location) {
        return findByLocationAndUniversityStatus(location, UniversityStatus.ACTIVE);
    }

    List<University> findByLocationAndUniversityStatus(String location, UniversityStatus status);

    default List<University> findAllByStatusIsActive() {
        return findAllByUniversityStatus(UniversityStatus.ACTIVE);
    }

    List<University> findAllByUniversityStatus(UniversityStatus status);
}
