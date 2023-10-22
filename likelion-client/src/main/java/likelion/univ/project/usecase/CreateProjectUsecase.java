package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.NotAdminForbiddenException;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateProjectUsecase {

    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final ProjectService projectService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private final UserAdaptor userAdaptor;
    private final ProjectAdaptor projectAdaptor;

    public ProjectIdResponseDto excute(ProjectRequestDto projectRequestDto) {

        User user = authentiatedUserUtils.getCurrentUser();
        if(user.getAuthInfo().getRole() != Role.ADMIN) {
            throw new NotAdminForbiddenException();
        }

        Project createdProject = projectService.createProject(projectRequestDto.toEntity());
        Long id = createdProject.getId();
        Project project = projectAdaptor.findById(id);
        projectImageService.addImage(
                projectRequestDto.getImageUrl().stream()
                        .map(imageUrl -> new Image(project, imageUrl))
                        .collect(Collectors.toList()));
        projectMemberService.addMembers(project,
                projectRequestDto.getMembers().stream()
                .map(member -> userAdaptor.findById(member))
                .collect(Collectors.toList()));

        return ProjectIdResponseDto.of(id);
    }
}
