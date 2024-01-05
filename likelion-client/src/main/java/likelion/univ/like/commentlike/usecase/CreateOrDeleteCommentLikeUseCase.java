package likelion.univ.like.commentlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.like.commentlike.dto.CommentLikeRequestDto;
import likelion.univ.domain.like.commentlike.dto.request.CommentLikeCommand;
import likelion.univ.domain.like.commentlike.dto.response.CommentLikeIdData;
import likelion.univ.domain.like.commentlike.service.CommentLikeDomainService;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateOrDeleteCommentLikeUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentLikeDomainService commentLikeDomainService;
    public boolean execute(CommentLikeRequestDto request) throws NotAuthorizedException {
        CommentLikeCommand serviceDto = getServiceDto(request);
        boolean isCreated = commentLikeDomainService.createOrDeleteCommentLike(serviceDto);
        return isCreated;
    }

    private CommentLikeCommand getServiceDto(CommentLikeRequestDto request) {
        return CommentLikeCommand.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .commentId(request.getCommentId())
                .build();
    }

}
