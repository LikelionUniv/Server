package likelion.univ.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.commentlike.dto.CommentLikeCreateRequestDto;
import likelion.univ.domain.commentlike.dto.CommentLikeCreateServiceDto;
import likelion.univ.domain.commentlike.dto.CommentLikeResponseDto;
import likelion.univ.domain.commentlike.service.CommentLikeDomainService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCommentLikeUseCase {
    private final AuthentiatedUserUtils userUtils;
    private final CommentLikeDomainService commentLikeDomainService;
    public SuccessResponse<?> execute(CommentLikeCreateRequestDto request) {
        CommentLikeResponseDto response = commentLikeDomainService.createLikeComment(serviceDtoBy(request));
        return SuccessResponse.of(response);
    }

    private CommentLikeCreateServiceDto serviceDtoBy(CommentLikeCreateRequestDto request) {
        return CommentLikeCreateServiceDto.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .commentId(request.getCommentId())
                .build();
    }

}
