package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    public List<ProjectImage> findByProject(Project project);
    public void deleteByProject(Project project);
}
