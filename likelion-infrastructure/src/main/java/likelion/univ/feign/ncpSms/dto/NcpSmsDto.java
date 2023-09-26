package likelion.univ.feign.ncpSms.dto;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NcpSmsDto {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
