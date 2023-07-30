package likelion.univ.domain.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponseDto<T> {
    T data;
    String message;
}
