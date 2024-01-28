package likelion.univ.domain.recruit.repository;

import likelion.univ.domain.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findAllByGenerationAndUniversityName(Integer generation, String universityName);
    Boolean existsByEmailAndGeneration(String email, Integer generation);
}
