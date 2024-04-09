package likelion.univ.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import likelion.univ.domain.comment.dto.request.CreateParentCommentCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateParentRequestDto {

    @NotBlank
    @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
    private String body;

    public CreateParentCommentCommand toCommand(Long postId, Long userId) {
        return CreateParentCommentCommand.builder()
                .postId(postId)
                .loginUserId(userId)
                .body(body)
                .build();
    }
}
