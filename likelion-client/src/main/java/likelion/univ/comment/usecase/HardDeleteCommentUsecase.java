package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class HardDeleteCommentUsecase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public void execute(Long commentId) {
        commentDomainService.deleteCommentHard(serviceDtoBy(commentId));
    }

    private DeleteCommentCommand serviceDtoBy(Long commentId) {
        return DeleteCommentCommand.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
