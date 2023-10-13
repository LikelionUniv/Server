package likelion.univ.domain.comment.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.*;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDomainService {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;

    public CommentCommandResponseDto createParentComment(CommentCreateParentServiceDto request) {
        Comment parentComment = parentCommentBy(request);
        Long savedId = commentAdaptor.save(parentComment);
        return CommentCommandResponseDto.of(savedId);
    }


    public CommentCommandResponseDto createChildComment(CommentCreateChildServiceDto request) {
        Comment childComment = childCommentBy(request);
        Long savedId = commentAdaptor.save(childComment);
        return CommentCommandResponseDto.of(savedId);
    }


    public CommentCommandResponseDto updateCommentBody(CommentUpdateServiceDto request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long updatedId = findComment.updateBody(request.getBody());
            return CommentCommandResponseDto.of(updatedId);
        }
        throw new NotAuthorizedException();
    }

    public CommentCommandResponseDto deleteCommentSoft(CommentDeleteServiceDto request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long deletedId = findComment.softDelete();
            return CommentCommandResponseDto.of(deletedId);
        }
        throw new NotAuthorizedException();
    }

    public void deleteCommentHard(CommentDeleteServiceDto request) {
        Comment findComment = commentAdaptor.findById(request.getCommentId());
        commentAdaptor.delete(findComment);
    }

    /* --------------- 내부 편의 메서드 --------------- */
    private Comment parentCommentBy(CommentCreateParentServiceDto request) {
        return Comment.builder()
                .post(postAdaptor.findById(request.getPostId()))
                .author(userAdaptor.findById(request.getLoginUserId()))
                .body(request.getBody())
                .build();
    }
    private Comment childCommentBy(CommentCreateChildServiceDto request) {
        Comment comment = Comment.builder()
                .post(getPostFromParentComment(request))
                .author(userAdaptor.findById(request.getLoginUserId()))
                .body(request.getBody())
                .build();
        comment.setParent(parentCommentBy(request.getParentCommentId()));
        return comment;
    }

    private Post getPostFromParentComment(CommentCreateChildServiceDto request) {
        return commentAdaptor.findById(request.getParentCommentId()).getPost();
    }


    private Comment parentCommentBy(Long parentCommentId) {
        return commentAdaptor.findById(parentCommentId);
    }

    private boolean isAuthorized(CommentUpdateServiceDto request) {
        Long commentId = request.getCommentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.getLoginUserId();
        return authorId.equals(loginUserId);
    }

    private boolean isAuthorized(CommentDeleteServiceDto request) {
        Long commentId = request.getCommentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.getLoginUserId();
        return authorId.equals(loginUserId);
    }

    private Long getAuthorId(Long commentId) {
        return commentAdaptor.findById(commentId).getAuthor().getId();
    }

}
