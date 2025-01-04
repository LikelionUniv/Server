package likelion.univ.adminuser.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GraduateUsersRequest(
        @NotNull(message = "기수를 입력해 주세요.")
        @Schema(description = "기수", example = "11", required = true)
        Long ordinal,

        @NotEmpty(message = "유저 ID를 입력해 주세요.")
        @Schema(description = "유저 ID 리스트", required = true)
        List<Long> userIds
) {
}
