package likelion.univ.like.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.like.commentlike.dto.CommentLikeCreateRequestDto;
import likelion.univ.domain.like.commentlike.dto.CommentLikeCreateServiceDto;
import likelion.univ.domain.like.commentlike.dto.CommentLikeResponseDto;
import likelion.univ.domain.like.commentlike.service.CommentLikeDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCommentLikeUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentLikeDomainService commentLikeDomainService;
    public CommentLikeResponseDto execute(CommentLikeCreateRequestDto request) {
        return commentLikeDomainService.createLikeComment(serviceDtoBy(request));
    }

    private CommentLikeCreateServiceDto serviceDtoBy(CommentLikeCreateRequestDto request) {
        return CommentLikeCreateServiceDto.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .commentId(request.getCommentId())
                .build();
    }

}
