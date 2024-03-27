package likelion.univ.adminUser.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletedUserListRequestDto {

    @NotNull
    @NotEmpty(message = " 삭제할 유저 ID들을 입력해주세요.")
    @Schema(description = "유저 ID", example = "[111111, 222222]")
    private List<@NotNull @Positive Long> ids;
}
