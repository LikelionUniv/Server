package likelion.univ.domain.recruit.repository;

import likelion.univ.domain.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findAllByUniversityNameAndGeneration(String universityName, Integer generation);
}
