package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.domain.comment.dto.request.GetCommentCommand;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.CommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCommentUseCase {
    private final CommentDomainService commentDomainService;
    private final AuthenticatedUserUtils authenticatedUser;

    public List<CommentResponseDto> execute(Long postId) {
        CommentData commentData = commentDomainService.getComment(postId);

        Long postAuthorId = commentData.postAuthorId();
        List<ParentCommentData> parentCommentData = commentData.parentComments();
        List<ChildCommentData> childCommentData = commentData.childComments();

        List<CommentResponseDto> response = parentCommentData.stream().map(pc -> CommentResponseDto.of(pc, childCommentData, authenticatedUser.getCurrentUserId(), postAuthorId)).toList();
        return response;
    }
}
