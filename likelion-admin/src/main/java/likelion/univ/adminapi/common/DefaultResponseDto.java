package likelion.univ.adminapi.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
@Schema(description = "기본 응답")
public class DefaultResponseDto<T> {

    @Schema(description = "응답 코드", example = "RESPONSE_CODE")
    private String responseCode;

    @Schema(description = "응답 메세지", example = "응답 메세지")
    private String responseMessage;

    @Schema(description = "데이터")
    private T data;

    public DefaultResponseDto(final String responseCode, final String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static <T> DefaultResponseDto<T> responseDto(final String responseCode, final String responseMessage) {
        return  response(responseCode, responseMessage, null);
    }

    public static <T> DefaultResponseDto<T> response(final String responseCode, final String responseMessage, final T data) {
        return DefaultResponseDto.<T>builder()
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .data(data)
                .build();
    }
}
