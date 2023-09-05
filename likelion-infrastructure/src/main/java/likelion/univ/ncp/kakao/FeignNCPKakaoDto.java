package likelion.univ.ncp.kakao;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FeignNCPKakaoDto {
    private String requestId;
    private String requestTime;
    private String statusCode;
    private String statusName;

    private List<Messages> messages;

    public static class Messages{
        private String messageId;
        private String to;
        private String content;
        private String requestStatusCode;
        private String requestStatusName;
        private String requestStatusDesc;
        private boolean useSmsFailover;
    }
}
