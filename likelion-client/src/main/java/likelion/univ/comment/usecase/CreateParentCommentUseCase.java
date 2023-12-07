package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.request.CreateParentCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(Long postId, CommentCreateParentRequestDto createRequestDto) {
        CommentIdData response = commentDomainService.createParentComment(serviceDtoBy(postId, createRequestDto));
        return SuccessResponse.of(response);
    }

    private CreateParentCommentCommand serviceDtoBy(Long postId, CommentCreateParentRequestDto createParentRequest) {
        return CreateParentCommentCommand.builder()
                .postId(postId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(createParentRequest.getBody())
                .build();
    }
}
