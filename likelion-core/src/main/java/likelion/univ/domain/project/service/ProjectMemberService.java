package likelion.univ.domain.project.service;

import likelion.univ.domain.project.dto.ProjectSimpleDto;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    @Transactional
    public void updateProjectMember(Long id, ProjectSimpleDto projectSimpleDto) {
        Project project = projectRepository.findById(id).get();
        projectMemberRepository.deleteByProject(project); //기존 멤버 모두 삭제
        if(projectSimpleDto.getMembers() != null) {
            List<ProjectMember> members = projectSimpleDto.getMembers().stream()
                    .map(projectMemberDto -> new ProjectMember(project, userRepository.findById(projectMemberDto.getUserID()).get()))
                    .collect(Collectors.toList());
            projectMemberRepository.saveAll(members);
        }
    }

    @Transactional
    public void deleteProjectMember(Long id) {
        Project project = projectRepository.findById(id).get();
        projectMemberRepository.deleteByProject(project);
    }

//    public List<ProjectMember> getProjectMember(Long id) {
//        Project project = projectRepository.findById(id).get();
//        return projectMemberRepository.findByProject(project);
//    }
}
