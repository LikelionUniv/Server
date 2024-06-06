package likelion.univ.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import likelion.univ.domain.comment.dto.request.CreateChildCommentCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateChildRequestDto {

    @NotBlank
    @Schema(description = "대댓글 내용", example = "대댓글 내용입니다.")
    private String body;

    public CreateChildCommentCommand toCommand(Long parentCommentId, Long userId) {
        return CreateChildCommentCommand.builder()
                .parentCommentId(parentCommentId)
                .loginUserId(userId)
                .body(body)
                .build();
    }
}
