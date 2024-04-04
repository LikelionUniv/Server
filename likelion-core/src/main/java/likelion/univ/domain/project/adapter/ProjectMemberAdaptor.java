package likelion.univ.domain.project.adapter;

import java.util.List;
import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ProjectMemberAdaptor {

    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMember> findByProject(Project project) {
        return projectMemberRepository.findByProject(project);
    }

    public void saveAll(List<ProjectMember> projectMembers) {
        projectMemberRepository.saveAll(projectMembers);
    }

    public void deleteByProject(Project project) {
        projectMemberRepository.deleteByProject(project);
    }
}
