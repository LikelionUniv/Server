package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class UpdateProjectUsecase {

    private final ProjectService projectService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private final ProjectAdaptor projectAdaptor;
    private final UserAdaptor userAdaptor;

    public ProjectIdResponseDto excute(Long projectId, ProjectRequestDto projectRequestDto) {

        Project project = projectAdaptor.findById(projectId);

        List<Image> image = projectRequestDto.getImageUrl().stream()
                .map(imageUrl -> new Image(project, imageUrl))
                .collect(Collectors.toList());
        List<User> members = projectRequestDto.getMembers().stream()
                .map(member -> userAdaptor.findById(member.getId()))
                .collect(Collectors.toList());

        projectService.updateProject(projectId, projectRequestDto.toEntity());
        projectImageService.updateImage(project, image);
        projectMemberService.updateProjectMember(project, members);

        return ProjectIdResponseDto.of(projectId);
    }
}
