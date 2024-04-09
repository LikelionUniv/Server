package likelion.univ.comment.service;

import java.util.List;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.domain.comment.dto.request.CreateChildCommentCommand;
import likelion.univ.domain.comment.dto.request.CreateParentCommentCommand;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.dto.request.GetCommentCommand;
import likelion.univ.domain.comment.dto.request.UpdateCommentCommand;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.CommentData;
import likelion.univ.domain.comment.dto.response.DeleteCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.exception.CommentAlreadyDeletedException;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientCommentService {

    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public void createChildComment(CreateChildCommentCommand command) {
        Long postId = commentDomainService.createChildComment(command);
        // redis update
        PostCountInfo postCountInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = postCountInfo.getCommentCount();
        Long likeCount = postCountInfo.getLikeCount();
        updatePostCountInfoProcessor.execute(postId, ++commentCount, likeCount);
    }

    public void createParentComment(CreateParentCommentCommand command) {
        Long postId = command.postId();
        commentDomainService.createParentComment(command);
        // redis update
        PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = countInfo.getCommentCount();
        Long likeCount = countInfo.getLikeCount();
        updatePostCountInfoProcessor.execute(postId, ++commentCount, likeCount);
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Long loginUserId = userUtils.getCurrentUserId();
        CommentData commentData = commentDomainService.getComment(new GetCommentCommand(postId, loginUserId));
        Long postAuthorId = commentData.postAuthorId();
        List<ParentCommentData> parentCommentData = commentData.parentComments();
        List<ChildCommentData> childCommentData = commentData.childComments();
        return parentCommentData.stream()
                .map(pc -> CommentResponseDto.of(
                        pc,
                        childCommentData,
                        userUtils.getCurrentUserId(),
                        postAuthorId)
                ).toList();
    }

    public void hardDeleteComment(DeleteCommentCommand command) {
        commentDomainService.deleteCommentHard(command);
    }

    public void softDeleteComment(DeleteCommentCommand command) {
        DeleteCommentData deleteCommentData = commentDomainService.deleteCommentSoft(command);
        if (!deleteCommentData.isDeleted()) {
            throw new CommentAlreadyDeletedException();
        }
        Long postId = deleteCommentData.postId();
        // redis update
        PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = countInfo.getCommentCount();
        Long likeCount = countInfo.getLikeCount();
        updatePostCountInfoProcessor.execute(postId, --commentCount, likeCount);
    }

    public Long updateComment(UpdateCommentCommand command) {
        return commentDomainService.updateCommentBody(command);
    }
}
