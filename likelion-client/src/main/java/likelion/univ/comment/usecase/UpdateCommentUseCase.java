package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.request.CommentUpdateRequestDto;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.request.UpdateCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class UpdateCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public CommentIdData execute(Long commentId, CommentUpdateRequestDto request) {
        CommentIdData response = commentDomainService.updateCommentBody(serviceDtoBy(commentId, request));
        return response;
    }

    private UpdateCommentCommand serviceDtoBy(Long commentId, CommentUpdateRequestDto request) {
        return UpdateCommentCommand.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(request.getBody())
                .build();
    }
}
