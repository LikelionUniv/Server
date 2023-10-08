package likelion.univ.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CommentUpdateRequestDto {
    @NotBlank
    @Schema(description = "변경하려는 댓글 내용", example = "변경된 댓글 내용입니다.")
    private String body;
}
