package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.dto.CommentCommandResponseDto;
import likelion.univ.domain.comment.dto.CommentDeleteServiceDto;
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
        CommentCommandResponseDto response = commentDomainService.deleteCommentSoft(serviceDtoBy(commentId));
        return SuccessResponse.of(response);
    }

    private CommentDeleteServiceDto serviceDtoBy(Long commentId) {
        return CommentDeleteServiceDto.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
