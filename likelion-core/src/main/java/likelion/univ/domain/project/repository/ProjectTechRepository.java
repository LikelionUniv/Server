package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTechRepository extends JpaRepository<ProjectTech, Long> {

    public List<ProjectTech> findByProject(Project project);
    public void deleteByProject(Project project);
}
