package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.dto.response.SimpleCommentData;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SoftDeleteCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public SimpleCommentData execute(Long commentId) {
        SimpleCommentData response = commentDomainService.deleteCommentSoft(serviceDtoBy(commentId));

        Long postId = response.getPostId();
        PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = countInfo.getCommentCount();
        Long likeCount = countInfo.getLikeCount();

        updatePostCountInfoProcessor.execute(postId, --commentCount, likeCount);

        return response;
    }

    private DeleteCommentCommand serviceDtoBy(Long commentId) {
        return DeleteCommentCommand.builder()
                .commentId(commentId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
