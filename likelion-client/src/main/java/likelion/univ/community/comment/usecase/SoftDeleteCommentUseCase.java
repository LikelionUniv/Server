package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.exception.AuthorNotDetectedException;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SoftDeleteCommentUseCase {
    public final CommentAdaptor commentAdaptor;
    public final CommentDomainService commentDomainService;

    public SuccessResponse<?> execute(CommentRequestDto.DeleteComment deleteRequestDto) {
        if (!isCommentAuthor(deleteRequestDto)) {
            throw new AuthorNotDetectedException();
        }
        CommentServiceDto.DeleteComment deleteServiceDto = buildServiceDtoFrom(deleteRequestDto);
        CommentServiceDto.CUDResponse deleteCUDResponseDto = commentDomainService.deleteCommentSoft(deleteServiceDto);
        return SuccessResponse.of(deleteCUDResponseDto);
    }

    private static CommentServiceDto.DeleteComment buildServiceDtoFrom(CommentRequestDto.DeleteComment deleteRequestDto) {
        return CommentServiceDto.DeleteComment.builder()
                .id(deleteRequestDto.getCommentId())
                .build();
    }

    private boolean isCommentAuthor(CommentRequestDto.DeleteComment deleteRequestDto) {
        return deleteRequestDto.getUserId().equals(getUserIdFromComment(deleteRequestDto));
    }

    private Long getUserIdFromComment(CommentRequestDto.DeleteComment deleteRequestDto) {
        return commentAdaptor.findById(deleteRequestDto.getCommentId()).getAuthor().getId();
    }
}
