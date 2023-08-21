package likelion.univ.adminUser.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SendMailRequestDto {
    @NotBlank
    @ApiModelProperty(value = "수신 이메일 주소", example = "likelionUniv@gmail.com", required = true)
    private String[] toAddress;
    @NotBlank
    @ApiModelProperty(value = "제목", example = "(광고) 코드잇", required = true)
    private String title;
    @NotBlank
    @ApiModelProperty(value = "내용", example = "(광고) 코드잇", required = true)
    private String body;
    @NotBlank
    @ApiModelProperty(value = "사진 및 영상", example = "jpg, mp4", required = true)
    private List<MultipartFile> files;
}
