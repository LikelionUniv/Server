package likelion.univ.adminUser.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NcpRequestDto {
    /*--------------------------------------문자 메시지----------------------------------------------*/

    @Builder
    @Getter
    public static class NcpSmsRequestDto {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<PhoneNum> messages;
    }


    @Getter
    public static class SendMsgRequestDto {
        /*  @NotBlank
          @ApiModelProperty(value = "수신 번호", example = "01012341234", required = true)
          private String to;*/
        @NotBlank
        @ApiModelProperty(value = "내용", example = "안녕하세요.", required = true)
        private String content;
    }


    @Builder
    public static class PhoneNum{
        @NotBlank
        @ApiModelProperty(value = "전화번호", example = "01012341234", required = true)
        private String to;

    }

    /*--------------------------------------알림톡------------------------------------------------*/

    @Builder
    public static class NcpKakaoRequestDto {

        private String plusFriendId;
        private String templateCode;
        private List<SendKakaoRequestDto> messages;
    }

    @Getter
    public static class SendKakaoRequestDto {

        private String to;
        private String title;
        private String content;
    }


}