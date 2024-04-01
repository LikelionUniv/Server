package likelion.univ.domain.university.repository;

import java.util.List;
import java.util.Optional;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.entity.UniversityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
    Optional<University> findByName(String name);

    List<University> findByLocationAndUniversityStatus(String location, UniversityStatus status);

    List<University> findAllByUniversityStatus(UniversityStatus status);
}
