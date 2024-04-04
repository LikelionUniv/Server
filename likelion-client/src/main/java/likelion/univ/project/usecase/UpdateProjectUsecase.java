package likelion.univ.project.usecase;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.project.service.ProjectTechService;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.request.ProjectMemberRequestDto;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateProjectUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final ProjectService projectService;
    private final ProjectTechService projectTechService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private final ProjectAdaptor projectAdaptor;
    private final UserAdaptor userAdaptor;
    private final UniversityAdaptor universityAdaptor;

    @Transactional
    public ProjectIdResponseDto execute(Long projectId, ProjectRequestDto projectRequestDto) {

        Project project = projectAdaptor.findById(projectId);

        authenticatedUserUtils.checkIdentification(project.getAuthor().getId());

        List<String> techNames = projectRequestDto.getProjectTeches();

        List<ProjectImage> projectImage = projectRequestDto.getImageUrl().stream()
                .map(imageUrl -> new ProjectImage(project, imageUrl))
                .collect(Collectors.toList());

        Project editProject = projectRequestDto.toEntity();
        if (!projectRequestDto.getUniv().isEmpty()) {
            editProject.updateUniv(universityAdaptor.findByName(projectRequestDto.getUniv()));
        } else {
            editProject.updateUniv(null);
        }

        List<ProjectMemberRequestDto> projectMembersRequest = projectRequestDto.getProjectMembers();
        List<Long> ids = projectMembersRequest.stream()
                .map(projectMemberRequestDto -> projectMemberRequestDto.getUserId()).toList();
        List<User> requestUsers = userAdaptor.findAllByIdIn(ids);

        List<ProjectMember> projectMembers = projectMembersRequest.stream()
                .map(p -> matchProjectMembers(p, project, requestUsers)).toList();

        projectService.updateProject(projectId, editProject);
        projectTechService.updateProjectTech(project, techNames);
        projectImageService.updateImage(project, projectImage);
        projectMemberService.updateProjectMember(project, projectMembers);

        return ProjectIdResponseDto.of(projectId);
    }

    /* projectMemberRequestDto의 userId와 일치하는 user를 유저리스트에서 찾아서 ProjectMember로 변환합니다.*/
    private ProjectMember matchProjectMembers(ProjectMemberRequestDto projectMemberRequestDto, Project project,
                                              List<User> users) {
        return users.stream().distinct().filter(user -> user.getId().equals(projectMemberRequestDto.getUserId()))
                .findFirst().map(user -> createProjectMember(project, user, projectMemberRequestDto.getPart())).get();
    }

    private ProjectMember createProjectMember(Project project, User user, Part part) {
        return ProjectMember.builder()
                .project(project)
                .user(user)
                .part(part)
                .build();
    }
}
