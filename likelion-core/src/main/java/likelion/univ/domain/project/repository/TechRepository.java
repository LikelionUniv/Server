package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<Tech, Long> {
    Tech findTechByTechName(String techName);
}
