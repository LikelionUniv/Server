package likelion.univ.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.commentlike.dto.CommentLikeResponseDto;
import likelion.univ.domain.commentlike.dto.CommentLikeSwitchServiceDto;
import likelion.univ.domain.commentlike.service.CommentLikeDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SwitchCommentLikeUseCase {
    private final AuthentiatedUserUtils userUtils;
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
