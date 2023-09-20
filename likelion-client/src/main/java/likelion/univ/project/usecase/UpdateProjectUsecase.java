package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.project.service.ImageService;
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
    private final ImageService imageService;
    private final ProjectMemberService projectMemberService;
    private final ProjectAdaptor projectAdaptor;
    private final ImageAdaptor imageAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;
    private final UserAdaptor userAdaptor;

    public ProjectIdResponseDto excute(Long projectId, ProjectRequestDto projectRequestDto) {

        Project project = projectAdaptor.findById(projectId);

        String thon = projectRequestDto.getThon();
        Output outPut = projectRequestDto.getOutPut();
        String serviceName = projectRequestDto.getServiceName();
        int ordinal = projectRequestDto.getOrdinal();
        String univ = projectRequestDto.getUniv();
        LocalDate startDate = projectRequestDto.getStartDate();
        LocalDate endDate = projectRequestDto.getEndDate();
        String tech = projectRequestDto.getTech();
        String description = projectRequestDto.getDescription();
        String content = projectRequestDto.getContent();
        String projectUrl = projectRequestDto.getProjectUrl();
        List<Image> image = projectRequestDto.getImages().stream()
                .map(imageRequestDto -> Image.builder()
                        .name(imageRequestDto.getName())
                        .saved(imageRequestDto.getSaved())
                        .project(project)
                        .build())
                .collect(Collectors.toList());
        List<User> members = projectRequestDto.getMembers().stream()
                .map(member -> userAdaptor.findById(member.getId()))
                .collect(Collectors.toList());

        projectService.updateProject(projectId, thon, outPut, serviceName, ordinal, univ, startDate, endDate, tech, description, content, projectUrl);
        imageService.updateImage(project, image);
        projectMemberService.updateProjectMember(project, members);

//        List<Image> images = imageAdaptor.findByProject(project);
//        List<User> users = projectMemberAdaptor.findByProject(project).stream()
//                .map(projectMember -> projectMember.getUser())
//                .map(user -> userAdaptor.findById(user.getId()))
//                .collect(Collectors.toList());

        return ProjectIdResponseDto.of(projectId);
    }
}
