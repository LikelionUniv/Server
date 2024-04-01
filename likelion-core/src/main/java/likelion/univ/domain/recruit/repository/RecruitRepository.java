package likelion.univ.domain.recruit.repository;

import java.util.List;
import likelion.univ.domain.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findAllByGenerationAndUniversityName(Integer generation, String universityName);

    Boolean existsByEmailAndGeneration(String email, Integer generation);
}
