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

    public SuccessResponse<?> execute(CommentRequestDto.DeleteComment deleteRequestDto, Long commentId) {
        if (!isCommentAuthor(deleteRequestDto, commentId)) {
            throw new AuthorNotDetectedException();
        }
        CommentServiceDto.DeleteCommentRequest deleteServiceDto = buildServiceDtoBy(commentId);
        CommentServiceDto.CommandResponse deleteCommandResponseDto = commentDomainService.deleteCommentSoft(deleteServiceDto);
        return SuccessResponse.of(deleteCommandResponseDto);
    }

    private static CommentServiceDto.DeleteCommentRequest buildServiceDtoBy(Long commentId) {
        return CommentServiceDto.DeleteCommentRequest.builder()
                .id(commentId)
                .build();
    }
    private boolean isCommentAuthor(CommentRequestDto.DeleteComment deleteRequestDto, Long commentId) {
        return deleteRequestDto.getUserId().equals(getUserIdFromComment(commentId));
    }

    private Long getUserIdFromComment(Long commentId) {
        return commentAdaptor.findById(commentId).getAuthor().getId();
    }
}
