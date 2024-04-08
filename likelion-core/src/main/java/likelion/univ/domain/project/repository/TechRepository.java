package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.exception.ProjectNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<Tech, Long> {

    default Tech getById(Long id) {
        return findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    Tech findByTechName(String techName);
}
