package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.request.CreateChildCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChildCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(Long parentCommentId, CommentCreateChildRequestDto request) {
        CommentIdData response = commentDomainService.createChildComment(serviceDtoBy(parentCommentId, request));
        return SuccessResponse.of(response);
    }
    private CreateChildCommentCommand serviceDtoBy(Long parentCommentId, CommentCreateChildRequestDto request) {
        return CreateChildCommentCommand.builder()
                .parentCommentId(parentCommentId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(request.getBody())
                .build();
    }
}
