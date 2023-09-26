package likelion.univ.feign.ncpSms;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NcpSmsContent {
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
    @Builder
    @Getter
    public static class PhoneNum {
        private String to;
    }
}
