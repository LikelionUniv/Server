package likelion.univ.project.dto.request;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * thumnail 제외
 */

//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectRequestDto {

    @NotBlank
    private String thon;

    @NotBlank
    private Output outPut;

    @NotBlank
    private String serviceName;

    @NotBlank
    private long ordinal;

    private String univ;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @NotNull
    private String tech;

    private String description;
    private String content;
    private String projectUrl;
    private List<String> imageUrl;
    private List<ProjectMemberRequestDto> members;

//    @Builder
//    public ProjectRequestDto(Project project, List<Image> images, List<ProjectMember> members) {
//        this.thon = project.getThon();
//        this.outPut = project.getOutPut();
//        this.serviceName = project.getServiceName();
//        this.ordinal = project.getOrdinal();
//        this.univ = project.getUniv();
//        this.startDate = project.getStartDate();
//        this.endDate = project.getEndDate();
//        this.tech = project.getTech();
//        this.description = project.getDescription();
//        this.content = project.getContent();
//        this.projectUrl = project.getProjectUrl();
//        this.images = images.stream()
//                .map(image -> new ImageRequestDto(image))
//                .collect(Collectors.toList());
//        this.members = members.stream()
//                .map(user -> ProjectMemberRequestDto.builder()
//                        .id(user.getId())
//                        .build())
//                .collect(Collectors.toList());
//    }

    public Project toEntity() {
        return Project.builder()
                .thon(thon)
                .outPut(outPut)
                .serviceName(serviceName)
                .ordinal(ordinal)
                .univ(univ)
                .startDate(startDate)
                .endDate(endDate)
                .tech(tech)
                .description(description)
                .content(content)
                .projectUrl(projectUrl)
                .build();
    }

}
