package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProjectResponseDto {

    private Long id;
    private String activity;
    private Output outPut;
    private String serviceName;
    private long ordinal;
    private String univ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String content;
    private String productionUrl;
    private List<String> projectTech;
    private List<String> imageUrl;
    private List<ProjectMemberResponseDto> members;

    public static ProjectResponseDto of(Project project, List<Tech> projectTeches, List<Image> images, List<User> users) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .activity(project.getActivity())
                .outPut(project.getOutPut())
                .serviceName(project.getServiceName())
                .ordinal(project.getOrdinal())
               //.univ(project.getUniv())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .description(project.getDescription())
                .content(project.getContent())
                .productionUrl(project.getProductionUrl())
                .projectTech(
                        projectTeches.stream()
                                .map(tech -> tech.getTechName())
                                .collect(Collectors.toList()))
                .imageUrl(
                        images.stream()
                                .map(image -> image.getImageUrl())
                                .collect(Collectors.toList())
                )
                .members(
                        users.stream()
                                .map(user -> ProjectMemberResponseDto.of(user.getId(), user.getProfile().getName()))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
