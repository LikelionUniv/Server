package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;

    @Transactional
    public void addMembers(Project project, List<User> users){
        List<ProjectMember> members = users.stream()
                .map(user -> new ProjectMember(project, user))
                .collect(Collectors.toList());
        projectMemberAdaptor.saveAll(members);
    }

    @Transactional
    public void updateProjectMember(Project project, List<User> users) {
        projectMemberAdaptor.deleteByProject(project); //기존 멤버 모두 삭제
        if(users != null) {
            List<ProjectMember> members = users.stream()
                    .map(user -> new ProjectMember(project, user))
                    .collect(Collectors.toList());
            projectMemberAdaptor.saveAll(members);
        }
    }

    @Transactional
    public void deleteProjectMember(Long id) {
        Project project = projectAdaptor.findById(id);
        projectMemberAdaptor.deleteByProject(project);
    }

//    public List<ProjectMember> getProjectMember(Long id) {
//        Project project = projectRepository.findById(id).get();
//        return projectMemberRepository.findByProject(project);
//    }
}
