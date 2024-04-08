package likelion.univ.domain.project.service;

import java.util.List;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Transactional
    public void updateProjectMember(Project project, List<ProjectMember> projectMembers) {
        projectMemberRepository.deleteByProject(project); //기존 멤버 모두 삭제
        if (projectMembers != null) {
            projectMemberRepository.saveAll(projectMembers);
        }
    }

    @Transactional
    public void deleteProjectMember(Long id) {
        Project project = projectRepository.getById(id);
        projectMemberRepository.deleteByProject(project);
    }
}
