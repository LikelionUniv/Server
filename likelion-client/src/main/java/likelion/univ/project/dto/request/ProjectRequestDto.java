package likelion.univ.project.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectRequestDto {

    @NotBlank
    @Schema(example = "해커톤")
    private String activity;

    @NotBlank
    @Schema(example = "WEB")
    private Output outPut;

    @NotBlank
    @Schema(example = "Likelion Univ프로젝트")
    private String serviceName;

    @NotBlank
    @Schema(example = "11")
    private long ordinal;

    @Schema(example = "홍익대학교")
    private String univ;

    @NotBlank
    @Schema(example = "2023-10-10")
    private LocalDate startDate;

    @NotBlank
    @Schema(example = "2023-11-14")
    private LocalDate endDate;

    @Schema(example = "Likelion Univ 1기 프로젝트입니다.")
    private String description;

    @Schema(example = "멋쟁이사자처럼 프로젝트를 진행하고 있습니다.")
    private String content;

    @Schema(example = "www.likelionuniv.com")
    private String productionUrl;

    @NotNull
    @Schema(example = "[\"스프링부트\",\"리액트\"]")
    private List<String> projectTeches;

    private List<String> imageUrl;

    @Schema(example = "[1, 2, 3]")
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
