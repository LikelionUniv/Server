package likelion.univ.adminUser.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateUserRequestDto {
    @NotBlank
    @ApiModelProperty(value = "이름", example = "멋쟁이", required = true)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "전공", example = "컴퓨터공학과", required = true)
    private String major;
    @NotBlank
    @ApiModelProperty(value = "파트", example = "BACKEND", required = true)
    private String part;
    @NotBlank
    @ApiModelProperty(value = "기수", example = "11", required = true)
    private Long ordinal;
    @NotBlank
    @ApiModelProperty(value = "이메일", example = "likelionUniv@gmail.com", required = true)
    private String email;


}