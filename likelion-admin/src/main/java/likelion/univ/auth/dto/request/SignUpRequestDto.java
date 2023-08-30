package likelion.univ.auth.dto.request;

import likelion.univ.domain.user.entity.UniversityInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String UniversityName;
    @NotNull
    private String major;
}
