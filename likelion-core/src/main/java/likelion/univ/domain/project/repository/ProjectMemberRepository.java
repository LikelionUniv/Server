package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    @Query("SELECT m FROM ProjectMember m join fetch m.user where m.project = :project")
    public List<ProjectMember> findByProject(Project project);
    public void deleteByProject(Project project);
}
