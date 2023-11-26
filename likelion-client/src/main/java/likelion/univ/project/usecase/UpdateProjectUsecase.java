package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.exception.ProjectNotAuthorization;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.project.service.ProjectTechService;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class UpdateProjectUsecase {

    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final ProjectService projectService;
    private final ProjectTechService projectTechService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private final ProjectAdaptor projectAdaptor;
    private final ProjectTechAdaptor projectTechAdaptor;
    private final UserAdaptor userAdaptor;
    private final UniversityAdaptor universityAdaptor;

    public ProjectIdResponseDto excute(Long projectId, ProjectRequestDto projectRequestDto) {

        Project project = projectAdaptor.findById(projectId);
        User user = authentiatedUserUtils.getCurrentUser();

        if(user.getId() != project.getAuthor().getId())
            throw new ProjectNotAuthorization();

        List<String> techNames = projectRequestDto.getProjectTeches();
        List<Tech> techList = techNames.stream()
                .flatMap(techName -> projectTechAdaptor.findByName(techName).stream())
                .collect(Collectors.toList());

        List<Image> image = projectRequestDto.getImageUrl().stream()
                .map(imageUrl -> new Image(project, imageUrl))
                .collect(Collectors.toList());
        List<User> members = projectRequestDto.getMembers().stream()
                .map(member -> userAdaptor.findById(member))
                .collect(Collectors.toList());

        Project editProject = projectRequestDto.toEntity();
        if(!projectRequestDto.getUniv().isEmpty())
            editProject.updateUniv(universityAdaptor.findByName(projectRequestDto.getUniv()));
        else
            editProject.updateUniv(null);

        projectService.updateProject(projectId, editProject);
        projectTechService.updateProjectTech(project,techList);
        projectImageService.updateImage(project, image);
        projectMemberService.updateProjectMember(project, members);

        return ProjectIdResponseDto.of(projectId);
    }
}
