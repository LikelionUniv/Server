package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.CommentUpdateRequestDto;
import likelion.univ.domain.comment.dto.CommentCommandResponseDto;
import likelion.univ.domain.comment.dto.CommentUpdateServiceDto;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class UpdateCommentUseCase {
    private final AuthentiatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(Long commentId, CommentUpdateRequestDto request) {
        CommentCommandResponseDto response = commentDomainService.updateCommentBody(serviceDtoBy(commentId, request));
        return SuccessResponse.of(response);
    }

    private CommentUpdateServiceDto serviceDtoBy(Long commentId, CommentUpdateRequestDto request) {
        return CommentUpdateServiceDto.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(request.getBody())
                .build();
    }
}
