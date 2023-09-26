package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    public List<ProjectMember> findByProject(Project project);
    public void deleteByProject(Project project);
}
