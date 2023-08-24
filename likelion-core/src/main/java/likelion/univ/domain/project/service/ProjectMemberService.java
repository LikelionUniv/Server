package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ProjectAdapter;
import likelion.univ.domain.project.adapter.ProjectMemberAdapter;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.user.adaptor.UserAdaptor;
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

    private final ProjectAdapter projectAdapter;
    private final ProjectMemberAdapter projectMemberAdapter;
    private final UserAdaptor userAdaptor;

//    public void addMembers(Long id, ProjectRequestDto projectRequestDto){
//        Optional<Project> optionalProject = projectAdaptor.findById(id);
//        if (optionalProject.isPresent()) {
//            List<ProjectMember> members = new ArrayList<>();
//            Project project = optionalProject.get();
//            for(ProjectMemberRequestDto projectMemberRequestDto : projectRequestDto.getMembers()){
//                ProjectMember projectMember = new ProjectMember(project,projectMemberRequestDto.getUser());
//                members.add(projectMember);
//            }
//            projecMemberAdaptor.saveAll(members);
//        }
//    }

    @Transactional
    public void updateProjectMember(Project project, List<User> users) {
        projectMemberAdapter.deleteByProject(project); //기존 멤버 모두 삭제
        if(users != null) {
            List<ProjectMember> members = users.stream()
                    .map(user -> new ProjectMember(project, user))
                    .collect(Collectors.toList());
            projectMemberAdapter.saveAll(members);
        }
    }

    @Transactional
    public void deleteProjectMember(Long id) {
        Project project = projectAdapter.findById(id).get();
        projectMemberAdapter.deleteByProject(project);
    }

//    public List<ProjectMember> getProjectMember(Long id) {
//        Project project = projectRepository.findById(id).get();
//        return projectMemberRepository.findByProject(project);
//    }
}
