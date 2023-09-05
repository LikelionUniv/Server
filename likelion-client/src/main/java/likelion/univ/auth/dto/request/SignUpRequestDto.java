package likelion.univ.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import likelion.univ.domain.user.entity.UniversityInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    @ApiModelProperty(value = "이름", example = "김슬기", required = true)
    private String name;
    @NotNull
    @ApiModelProperty(value = "대학명", example = "홍익대학교", required = true)
    private String universityName;
    @NotNull
    @ApiModelProperty(value = "학과", example = "컴퓨터공학과", required = true)
    private String major;
}
