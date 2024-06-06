package likelion.univ.project.service;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.project.service.ProjectTechService;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.project.dto.request.ProjectMemberRequestDto;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientProjectService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final ProjectService projectService;
    private final ProjectTechService projectTechService;
    private final ProjectImageService projectImageService;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UniversityRepository universityRepository;
    private final ProjectMemberService projectMemberService;
    private final ProjectRepository projectRepository;

    public Long create(Long userId, ProjectRequestDto projectRequestDto) {
        User user = userRepository.getById(userId);
        Project request = projectRequestDto.toEntity();
        request.updateAuthor(user);
        if (!projectRequestDto.getUniv().isEmpty()) {
            request.updateUniv(universityRepository.getByName(projectRequestDto.getUniv()));
        }
        Project project = projectService.createProject(request);
        List<String> techNames = projectRequestDto.getProjectTeches();
        projectTechService.addProjectTech(project, techNames.stream().map(tech -> tech.toUpperCase()).toList());
        projectImageService.addImage(
                projectRequestDto.getImageUrl().stream()
                        .map(imageUrl -> new ProjectImage(project, imageUrl))
                        .toList()
        );
        List<ProjectMemberRequestDto> projectMembersRequest = projectRequestDto.getProjectMembers();
        List<Long> ids = projectMembersRequest.stream()
                .map(ProjectMemberRequestDto::getUserId)
                .toList();
        List<User> requestUsers = userRepository.findAllByIdsExactly(ids);
        List<ProjectMember> projectMembers = projectMembersRequest.stream()
                .map(pm -> matchProjectMembers(pm, project, requestUsers))
                .toList();
        projectMemberRepository.saveAll(projectMembers);
        return project.getId();
    }

    public void update(Long projectId, ProjectRequestDto projectRequestDto) {
        Project project = projectRepository.getById(projectId);
        authenticatedUserUtils.checkIdentification(project.getAuthor().getId());
        List<String> techNames = projectRequestDto.getProjectTeches();
        List<ProjectImage> projectImage = projectRequestDto.getImageUrl().stream()
                .map(imageUrl -> new ProjectImage(project, imageUrl))
                .collect(Collectors.toList());
        Project editProject = projectRequestDto.toEntity();
        if (!projectRequestDto.getUniv().isEmpty()) {
            editProject.updateUniv(universityRepository.getByName(projectRequestDto.getUniv()));
        } else {
            editProject.updateUniv(null);
        }
        List<ProjectMemberRequestDto> projectMembersRequest = projectRequestDto.getProjectMembers();
        List<Long> ids = projectMembersRequest.stream()
                .map(ProjectMemberRequestDto::getUserId)
                .toList();
        List<User> requestUsers = userRepository.findAllByIdsExactly(ids);
        List<ProjectMember> projectMembers = projectMembersRequest.stream()
                .map(p -> matchProjectMembers(p, project, requestUsers))
                .toList();
        projectService.updateProject(projectId, editProject);
        projectTechService.updateProjectTech(project, techNames);
        projectImageService.updateImage(project, projectImage);
        projectMemberService.updateProjectMember(project, projectMembers);
    }

    // projectMemberRequestDto의 userId와 일치하는 user를 유저리스트에서 찾아서 ProjectMember로 변환합니다.
    private ProjectMember matchProjectMembers(
            ProjectMemberRequestDto projectMemberRequestDto,
            Project project,
            List<User> users
    ) {
        return users.stream()
                .distinct()
                .filter(user -> user.getId().equals(projectMemberRequestDto.getUserId()))
                .map(user -> ProjectMember.builder()
                        .project(project)
                        .user(user)
                        .part(projectMemberRequestDto.getPart())
                        .build())
                .findFirst()
                .orElseThrow();
    }

    public void delete(Long projectId) {
        Project project = projectRepository.getById(projectId);
        authenticatedUserUtils.checkIdentification(project.getAuthor().getId());
        projectTechService.deleteProjectTech(projectId);
        projectImageService.deleteImage(projectId);
        projectMemberService.deleteProjectMember(projectId);
        projectService.deleteProject(projectId);
    }
}
