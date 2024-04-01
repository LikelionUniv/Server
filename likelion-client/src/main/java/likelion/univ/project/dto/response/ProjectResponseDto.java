package likelion.univ.project.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.Builder;
import lombok.Data;

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

    public static ProjectResponseDto of(Project project, String univ, List<Tech> projectTeches,
                                        List<ProjectImage> projectImages,
                                        List<ProjectMemberResponseDto> projectMembers) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .activity(project.getActivity())
                .outPut(project.getOutPut())
                .serviceName(project.getServiceName())
                .ordinal(project.getOrdinal())
                .univ(univ)
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
                        projectImages.stream()
                                .map(image -> image.getImageUrl())
                                .collect(Collectors.toList())
                )
                .members(projectMembers)
                .build();
    }
}
