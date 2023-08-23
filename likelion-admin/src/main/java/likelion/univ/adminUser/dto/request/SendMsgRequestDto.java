package likelion.univ.adminUser.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SendMsgRequestDto {
    @NotBlank
    @ApiModelProperty(value = "수신 번호", example = "01012341234", required = true)
    private String to;
    @NotBlank
    @ApiModelProperty(value = "내용", example = "안녕하세요.", required = true)
    private String content;
}
