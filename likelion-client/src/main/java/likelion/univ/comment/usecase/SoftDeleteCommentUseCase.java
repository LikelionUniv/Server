package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.dto.response.DeleteCommentData;
import likelion.univ.domain.comment.exception.CommentAlreadyDeletedException;
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

    public void execute(Long commentId) {
        Long loginUserId = userUtils.getCurrentUserId();
        DeleteCommentData deleteCommentData = commentDomainService.deleteCommentSoft(DeleteCommentCommand.of(commentId, loginUserId));

        if (deleteCommentData.isDeleted()) {
            Long postId = deleteCommentData.postId();
            // redis update
            PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
            Long commentCount = countInfo.getCommentCount();
            Long likeCount = countInfo.getLikeCount();
            updatePostCountInfoProcessor.execute(postId, --commentCount, likeCount);
            return;
        }
        throw new CommentAlreadyDeletedException();
    }
}
