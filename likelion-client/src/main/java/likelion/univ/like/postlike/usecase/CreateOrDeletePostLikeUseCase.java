package likelion.univ.like.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
import likelion.univ.domain.like.postlike.service.PostLikeDomainService;
import likelion.univ.like.postlike.dto.PostLikeRequestDto;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateOrDeletePostLikeUseCase {
    private final PostLikeDomainService postLikeDomainService;
    private final AuthenticatedUserUtils userUtils;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public boolean execute(PostLikeRequestDto request) {
        boolean hasCreated = postLikeDomainService.createOrDeletePostLike(getServiceDto(request));
        Long postId = request.postId();
        PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = countInfo.getCommentCount();
        Long likeCount = countInfo.getLikeCount();
        if (hasCreated) {
            updatePostCountInfoProcessor.execute(postId, commentCount, ++likeCount);
        } else {
            updatePostCountInfoProcessor.execute(postId, commentCount, --likeCount);
        }

        return hasCreated;
    }

    private PostLikeCommand getServiceDto(PostLikeRequestDto request) {
        return new PostLikeCommand(request.postId(), userUtils.getCurrentUserId());
    }
}
