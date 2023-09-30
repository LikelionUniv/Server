package likelion.univ.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.commentlike.dto.CommentLikeRequestDto;
import likelion.univ.domain.commentlike.dto.CommentLikeServiceDto;
import likelion.univ.domain.commentlike.service.CommentLikeDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SwitchCommentLikeUseCase {
    private final CommentLikeDomainService commentLikeDomainService;

    public CommentLikeServiceDto.CommandResponse execute(CommentLikeRequestDto.Switch switchRequest, Long likeCommentId) {
        CommentLikeServiceDto.switchLikeCommentRequest switchLikeCommentRequest = buildServiceDtoBy(switchRequest, likeCommentId);
        return commentLikeDomainService.switchLikeComment(switchLikeCommentRequest);
    }

    private CommentLikeServiceDto.switchLikeCommentRequest buildServiceDtoBy(CommentLikeRequestDto.Switch switchRequest, Long likeCommentId) {
        return CommentLikeServiceDto.switchLikeCommentRequest.builder()
                .likeCommentId(likeCommentId)
                .userId(switchRequest.getUserId())
                .build();
    }
}
