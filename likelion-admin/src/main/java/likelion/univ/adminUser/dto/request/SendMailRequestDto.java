package likelion.univ.adminUser.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SendMailRequestDto {
    @NotBlank
    @Schema(description = "제목", example = "(광고) 코드잇", required = true)
    private String title;
    @NotBlank
    @Schema(description = "내용", example = "(광고) 코드잇", required = true)
    private String body;
    @Schema(description = "사진 및 영상", example = "jpg, mp4", required = true)
    private List<MultipartFile> files;
}
