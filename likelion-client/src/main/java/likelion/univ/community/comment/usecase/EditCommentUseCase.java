package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.exception.AuthorNotDetectedException;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EditCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.CommandResponse execute(CommentRequestDto.EditComment editRequestDto, Long commentId) {
        if (!isCommentAuthor(editRequestDto, commentId)) {
            throw new AuthorNotDetectedException();
        }
        CommentServiceDto.EditCommentRequest editServiceDto = buildServiceDtoBy(editRequestDto, commentId);
        return commentDomainService.editCommentBody(editServiceDto);
    }

    private static CommentServiceDto.EditCommentRequest buildServiceDtoBy(CommentRequestDto.EditComment editRequestDto, Long commentId) {
        return CommentServiceDto.EditCommentRequest.builder()
                .id(commentId)
                .body(editRequestDto.getBody())
                .build();
    }

    private boolean isCommentAuthor(CommentRequestDto.EditComment editRequestDto, Long commentId) {
        return editRequestDto.getUserId().equals(getUserIdFromComment(commentId));
    }

    private Long getUserIdFromComment(Long commentId) {
        return commentAdaptor.findById(commentId).getAuthor().getId();
    }
}
