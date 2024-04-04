package likelion.univ.project.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Schema(example = "WEB")
    private Output outPut;

    @NotBlank
    @Schema(example = "Likelion Univ프로젝트")
    private String serviceName;

    @NotNull
    @Schema(example = "11")
    private Long ordinal;

    @Schema(example = "홍익대학교")
    private String univ;

    @NotNull
    @Schema(example = "2023-10-10")
    private LocalDate startDate;

    @NotNull
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

    @Schema(example = "[{" +
                      "   \"userId\" : \"3\"," +
                      "   \"part\" : \"BACKEND\"" +
                      "},{" +
                      "   \"userId\" : \"2\"," +
                      "   \"part\" : \"FRONTEND\"" +
                      "}]")
    private List<ProjectMemberRequestDto> projectMembers;

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
