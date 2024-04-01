package likelion.univ.domain.project.repository;

import java.util.List;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
    public List<ProjectImage> findByProject(Project project);

    public void deleteByProject(Project project);
}
