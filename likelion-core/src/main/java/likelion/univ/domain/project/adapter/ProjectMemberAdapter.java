package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ProjectMemberAdapter {

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
