package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProjectResponseDto {

    private String thon;
    private Output outPut;
    private String serviceName;
    private long ordinal;
    private String univ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tech;
    private String description;
    private String content;
    private String projectUrl;
    private List<String> imageUrl;
    private List<ProjectMemberResponseDto> members;

    public static ProjectResponseDto of(Project project, List<Image> images, List<User> users) {
        return ProjectResponseDto.builder()
                .thon(project.getThon())
                .outPut(project.getOutPut())
                .serviceName(project.getServiceName())
                .ordinal(project.getOrdinal())
                .univ(project.getUniv())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .tech(project.getTech())
                .description(project.getDescription())
                .content(project.getContent())
                .projectUrl(project.getProjectUrl())
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
