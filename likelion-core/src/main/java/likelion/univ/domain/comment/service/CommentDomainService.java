package likelion.univ.domain.comment.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.request.*;
import likelion.univ.domain.comment.dto.response.*;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.commentlike.adaptor.CommentLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDomainService {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentLikeAdaptor commentLikeAdaptor;

    public CommentData getComment(GetCommentCommand command) {
        Long postId = command.postId();
        Long loginUserId = command.loginUserId();
        Long authorId = postAdaptor.findById(postId).getAuthor().getId();

        // comment entity data
        List<Comment> parentComments = commentAdaptor.findParentCommentsByPostId(postId);
        List<Comment> childComments = commentAdaptor.findChildCommentsByPostId(postId);

        List<ParentCommentData> parentCommentData = parentComments.stream().map(i -> ParentCommentData.of(i, commentLikeAdaptor.existsByCommentIdAndUserId(i.getId(), loginUserId))).toList();
        List<ChildCommentData> childCommentData = childComments.stream().map(i -> ChildCommentData.of(i, commentLikeAdaptor.existsByCommentIdAndUserId(i.getId(), loginUserId))).toList();

        return new CommentData(authorId, parentCommentData, childCommentData);
    }


    public void createParentComment(CreateParentCommentCommand request) {
        Comment parentComment = parentCommentBy(request);
        commentAdaptor.save(parentComment);
    }


    public Long createChildComment(CreateChildCommentCommand request) {
        Comment childComment = childCommentBy(request);
        Long postId = childComment.getPost().getId();
        commentAdaptor.save(childComment);
        return postId;
    }


    public Long updateCommentBody(UpdateCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long updatedCommentId = findComment.updateBody(request.getBody());
            return updatedCommentId;
        }
        throw new NotAuthorizedException();
    }

    public DeleteCommentData deleteCommentSoft(DeleteCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.commentId());
            Boolean isDeleted = findComment.softDelete();
            Long postId = findComment.getPost().getId();
            return new DeleteCommentData(isDeleted, postId);
        }
        throw new NotAuthorizedException();
    }

    public void deleteCommentHard(DeleteCommentCommand request) {
        Comment findComment = commentAdaptor.findById(request.commentId());
        commentAdaptor.delete(findComment);
    }



    /* --------------- 내부 편의 메서드 --------------- */
    private Comment parentCommentBy(CreateParentCommentCommand request) {
        return Comment.builder()
                .post(postAdaptor.findById(request.getPostId()))
                .author(userAdaptor.findById(request.getLoginUserId()))
                .body(request.getBody())
                .build();
    }
    private Comment childCommentBy(CreateChildCommentCommand request) {
        Comment comment = Comment.builder()
                .post(getPostFromParentComment(request))
                .author(userAdaptor.findById(request.getLoginUserId()))
                .body(request.getBody())
                .build();
        comment.setParent(commentAdaptor.findById(request.getParentCommentId()));
        return comment;
    }

    private Post getPostFromParentComment(CreateChildCommentCommand request) {
        Long parentCommentId = request.getParentCommentId();
        Post post = commentAdaptor.findById(parentCommentId).getPost();
        return post;
    }


    private boolean isAuthorized(UpdateCommentCommand request) {
        Long commentId = request.getCommentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.getLoginUserId();
        return authorId.equals(loginUserId);
    }

    private boolean isAuthorized(DeleteCommentCommand request) {
        Long commentId = request.commentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.loginUserId();
        return authorId.equals(loginUserId);
    }

    private Long getAuthorId(Long commentId) {
        return commentAdaptor.findById(commentId).getAuthor().getId();
    }

}
