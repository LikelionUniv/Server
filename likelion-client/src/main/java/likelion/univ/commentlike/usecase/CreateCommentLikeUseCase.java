package likelion.univ.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.commentlike.dto.CommentLikeRequestDto;
import likelion.univ.domain.commentlike.dto.CommentLikeServiceDto;
import likelion.univ.domain.commentlike.service.CommentLikeDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCommentLikeUseCase {
    private final CommentLikeDomainService commentLikeDomainService;

    public CommentLikeServiceDto.CommandResponse execute(CommentLikeRequestDto.Create createRequest) {
        CommentLikeServiceDto.createLikeCommentRequest createLikeCommentRequest = buildServiceDtoBy(createRequest);
        return commentLikeDomainService.createLikeComment(createLikeCommentRequest);
    }

    private CommentLikeServiceDto.createLikeCommentRequest buildServiceDtoBy(CommentLikeRequestDto.Create createRequest) {
        return CommentLikeServiceDto.createLikeCommentRequest.builder()
                .userId(createRequest.getUserId())
                .commentId(createRequest.getCommentId())
                .build();
    }

}
