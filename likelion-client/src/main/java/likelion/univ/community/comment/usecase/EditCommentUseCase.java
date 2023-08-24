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
public class EditCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.Response execute(CommentRequestDto.EditComment editRequestDto) {
        if (!isCommentAuthor(editRequestDto)) {
            throw new AuthorNotDetectedException();
        }
        CommentServiceDto.EditComment editServiceDto = buildServiceDtoFrom(editRequestDto);
        return commentDomainService.editCommentBody(editServiceDto);
    }

    private static CommentServiceDto.EditComment buildServiceDtoFrom(CommentRequestDto.EditComment editRequestDto) {
        return CommentServiceDto.EditComment.builder()
                .id(editRequestDto.getCommentId())
                .body(editRequestDto.getBody())
                .build();
    }

    private boolean isCommentAuthor(CommentRequestDto.EditComment editRequestDto) {
        return editRequestDto.getUserId().equals(getUserIdFromComment(editRequestDto));
    }

    private Long getUserIdFromComment(CommentRequestDto.EditComment editRequestDto) {
        return commentAdaptor.findById(editRequestDto.getCommentId()).getAuthor().getId();
    }
}
