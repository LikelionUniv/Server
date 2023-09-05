package likelion.univ.feign.ncp.sms.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FeignNCPSmsDto {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
