package likelion.univ.domain.project.service;

import java.util.List;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;

    @Transactional
    public void updateProjectMember(Project project, List<ProjectMember> projectMembers) {
        projectMemberAdaptor.deleteByProject(project); //기존 멤버 모두 삭제
        if (projectMembers != null) {
            projectMemberAdaptor.saveAll(projectMembers);
        }
    }

    @Transactional
    public void deleteProjectMember(Long id) {
        Project project = projectAdaptor.findById(id);
        projectMemberAdaptor.deleteByProject(project);
    }
    
}
