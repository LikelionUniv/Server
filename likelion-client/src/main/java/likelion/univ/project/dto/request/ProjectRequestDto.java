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
    private List<Long> members;

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
