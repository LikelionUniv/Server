package likelion.univ.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotNull
    @Schema(description = "이름", example = "김슬기", required = true)
    private String name;
    @NotNull
    @Schema(description = "대학명", example = "홍익대학교", required = true)
    private String universityName;
    @NotNull
    @Schema(description = "학과", example = "컴퓨터공학과", required = true)
    private String major;
    @NotNull
    @Schema(description = "기수", example = "12", required = true)
    private Long ordinal;
}
