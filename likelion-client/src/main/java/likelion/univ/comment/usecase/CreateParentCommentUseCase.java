package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.CommentCreateParentRequestDto;
import likelion.univ.domain.comment.dto.CommentCommandResponseDto;
import likelion.univ.domain.comment.dto.CommentCreateParentServiceDto;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(CommentCreateParentRequestDto createRequestDto) {
        CommentCommandResponseDto response = commentDomainService.createParentComment(serviceDtoBy(createRequestDto));
        return SuccessResponse.of(response);
    }

    private CommentCreateParentServiceDto serviceDtoBy(CommentCreateParentRequestDto createParentRequest) {
        return CommentCreateParentServiceDto.builder()
                .postId(createParentRequest.getPostId())
                .loginUserId(userUtils.getCurrentUserId())
                .body(createParentRequest.getBody())
                .build();
    }
}
