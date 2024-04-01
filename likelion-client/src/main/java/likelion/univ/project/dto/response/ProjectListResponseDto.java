package likelion.univ.project.dto.response;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectListResponseDto {

    private Long id;
    private String activity;
    private Output outPut;
    private String serviceName;
    private long ordinal;
    private String univ;
    private String description;
    private String content;
    private String imageUrl;

    public static ProjectListResponseDto of(Project project, String univ, String imageUrl) {
        return ProjectListResponseDto.builder()
                .id(project.getId())
                .activity(project.getActivity())
                .outPut(project.getOutPut())
                .serviceName(project.getServiceName())
                .ordinal(project.getOrdinal())
                .univ(univ)
                .description(project.getDescription())
                .content(project.getContent())
                .imageUrl(imageUrl)
                .build();
    }
}
