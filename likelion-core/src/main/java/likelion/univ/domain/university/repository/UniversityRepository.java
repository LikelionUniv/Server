package likelion.univ.domain.university.repository;

import likelion.univ.domain.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University,Long> {
    Optional<University> findByName(String name);
    List<University> findByLocation(String location);
}
