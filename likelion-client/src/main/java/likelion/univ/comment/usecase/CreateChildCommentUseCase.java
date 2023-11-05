package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.CommentCreateChildRequestDto;
import likelion.univ.domain.comment.dto.CommentCommandResponseDto;
import likelion.univ.domain.comment.dto.CommentCreateChildServiceDto;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChildCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(CommentCreateChildRequestDto request) {
        CommentCommandResponseDto response = commentDomainService.createChildComment(serviceDtoBy(request));
        return SuccessResponse.of(response);
    }
    private CommentCreateChildServiceDto serviceDtoBy(CommentCreateChildRequestDto request) {
        return CommentCreateChildServiceDto.builder()
                .parentCommentId(request.getParentCommentId())
                .loginUserId(userUtils.getCurrentUserId())
                .body(request.getBody())
                .build();
    }
}
