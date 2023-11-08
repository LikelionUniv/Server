package likelion.univ.project.dto.request;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * thumnail 제외
 */

//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectRequestDto {

    @NotBlank
    private String activity;

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

    private String description;
    private String content;
    private String productionUrl;
    @NotNull
    private List<String> teches;
    private List<String> imageUrl;
    private List<Long> members;

    public Project toEntity() {
        return Project.builder()
                .activity(activity)
                .outPut(outPut)
                .serviceName(serviceName)
                .ordinal(ordinal)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .content(content)
                .productionUrl(productionUrl)
                .build();
    }

}
