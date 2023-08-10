package likelion.univ.domain.university.repository;

import likelion.univ.domain.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University,Long> {
    University findByName(String name);

}
