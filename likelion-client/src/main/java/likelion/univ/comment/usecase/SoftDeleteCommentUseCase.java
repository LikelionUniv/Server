package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SoftDeleteCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(Long commentId) {
        CommentIdData response = commentDomainService.deleteCommentSoft(serviceDtoBy(commentId));
        return SuccessResponse.of(response);
    }

    private DeleteCommentCommand serviceDtoBy(Long commentId) {
        return DeleteCommentCommand.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
