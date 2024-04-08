package likelion.univ.domain.recruit.repository;

import java.util.List;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.exception.RecruitNotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    default Recruit getById(Long id) {
        return findById(id).orElseThrow(RecruitNotFound::new);
    }

    List<Recruit> findAllByGenerationAndUniversityName(Integer generation, String universityName);

    boolean existsByEmailAndGeneration(String email, Integer generation);
}
