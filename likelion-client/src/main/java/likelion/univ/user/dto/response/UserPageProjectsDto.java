package likelion.univ.user.dto.response;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPageProjectsDto {
    private Long projectId;
    private String serviceName;
    private Output outPut;
    private String description;
    private Long ordinal;
    private String universityName;
    private String activity;
    private String thumbnail;

    public static UserPageProjectsDto of(Project project) {
        return UserPageProjectsDto.builder()
                .projectId(project.getId())
                .serviceName(project.getServiceName())
                .outPut(project.getOutPut())
                .description(project.getDescription())
                .ordinal(project.getOrdinal())
                .universityName(project.getUniv().getName())
                .activity(project.getActivity())
                .thumbnail(getThumbnail(project))
                .build();
    }

    public static String getThumbnail(Project project) {
        return project.getProjectImages().isEmpty() ? null : project.getProjectImages().get(0).getImageUrl();
    }
}
