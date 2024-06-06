package likelion.univ.like.postlike.service;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
import likelion.univ.domain.like.postlike.service.PostLikeService;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ClientPostLikeService {

    private final PostLikeService postLikeService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public boolean createOrDelete(PostLikeCommand command) {
        boolean hasCreated = postLikeService.createOrDeletePostLike(command);
        Long postId = command.postId();
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
}
