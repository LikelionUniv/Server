package likelion.univ.like.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.commentlike.dto.CommentLikeResponseDto;
import likelion.univ.domain.like.commentlike.dto.CommentLikeSwitchServiceDto;
import likelion.univ.domain.like.commentlike.service.CommentLikeDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SwitchCommentLikeUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentLikeDomainService commentLikeDomainService;

    public SuccessResponse<?> execute(Long commentLikeId) {
        CommentLikeResponseDto response = commentLikeDomainService.switchLikeComment(serviceDtoBy(commentLikeId));
        return SuccessResponse.of(response);
    }

    private CommentLikeSwitchServiceDto serviceDtoBy(Long commentLikeId) {
        return CommentLikeSwitchServiceDto.builder()
                .commentLikeId(commentLikeId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
