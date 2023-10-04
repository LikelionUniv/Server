package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateProjectUsecase {

    private final ProjectService projectService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private final UserAdaptor userAdaptor;
    private final ProjectAdaptor projectAdaptor;

    public ProjectIdResponseDto excute(ProjectRequestDto projectRequestDto) {
        Project createdProject = projectService.createProject(
                projectRequestDto.getThon(),
                projectRequestDto.getOutPut(),
                projectRequestDto.getServiceName(),
                projectRequestDto.getOrdinal(),
                projectRequestDto.getUniv(),
                projectRequestDto.getStartDate(),
                projectRequestDto.getEndDate(),
                projectRequestDto.getTech(),
                projectRequestDto.getDescription(),
                projectRequestDto.getContent(),
                projectRequestDto.getProjectUrl()
        );
        Long id = createdProject.getId();
        Project project = projectAdaptor.findById(id);
        projectImageService.addImage(
                projectRequestDto.getImages().stream()
                        .map(imageRequestDto -> Image.builder()
                                .name(imageRequestDto.getName())
                                .saved(imageRequestDto.getSaved())
                                .project(project)
                                .build())
                        .collect(Collectors.toList()));
        projectMemberService.addMembers(project, projectRequestDto.getMembers().stream()
                .map(member -> userAdaptor.findById(member.getId()))
                .collect(Collectors.toList()));

//        List<Image> images = imageAdaptor.findByProject(project);
//        List<User> users = projectMemberAdaptor.findByProject(project).stream()
//                .map(projectMember -> projectMember.getUser())
//                .map(user -> userAdaptor.findById(user.getId()))
//                .collect(Collectors.toList());

        return ProjectIdResponseDto.of(id);
    }
}
