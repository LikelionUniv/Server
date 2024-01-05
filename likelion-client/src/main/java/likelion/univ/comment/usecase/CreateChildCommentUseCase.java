package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.domain.comment.dto.request.CreateChildCommentCommand;
import likelion.univ.domain.comment.dto.response.CreateCommentData;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChildCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public CreateCommentData execute(Long parentCommentId, CommentCreateChildRequestDto request) {
        CreateCommentData createCommentData = commentDomainService.createChildComment(serviceDtoBy(parentCommentId, request));

        // redis update
        Long postId = createCommentData.getPostId();
        PostCountInfo postCountInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = postCountInfo.getCommentCount();
        Long likeCount = postCountInfo.getLikeCount();
        updatePostCountInfoProcessor.execute(postId, ++commentCount, likeCount);

        return createCommentData;
    }
    private CreateChildCommentCommand serviceDtoBy(Long parentCommentId, CommentCreateChildRequestDto request) {
        return CreateChildCommentCommand.builder()
                .parentCommentId(parentCommentId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(request.getBody())
                .build();
    }
}
