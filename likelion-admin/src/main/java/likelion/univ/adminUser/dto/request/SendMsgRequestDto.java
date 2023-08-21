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
public class SendMsgRequestDto {
    @NotBlank
    @ApiModelProperty(value = "수신 번호", example = "01012341234", required = true)
    private String[] phoneNum;
    @NotBlank
    @ApiModelProperty(value = "내용", example = "안녕하세요.", required = true)
    private String msg;
}
