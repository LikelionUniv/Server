package likelion.univ.adminUser.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateUserRequestDto {
    @NotBlank
    @Schema(description = "이름", example = "멋쟁이", required = true)
    private String name;
    @NotBlank
    @Schema(description = "전공", example = "컴퓨터공학과", required = true)
    private String major;
    @NotBlank
    @Schema(description = "파트", example = "BACKEND", required = true)
    private String part;
    @NotBlank
    @Schema(description = "기수", example = "11", required = true)
    private Long ordinal;
    @NotBlank
    @Schema(description = "역할", example = "MANAGER", required = true)
    private String role;


}