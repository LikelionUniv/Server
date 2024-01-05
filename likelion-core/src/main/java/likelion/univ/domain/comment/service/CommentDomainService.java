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
import likelion.univ.domain.user.entity.User;
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
        User loginUser = userAdaptor.findById(loginUserId);

        // comment entity data
        List<Comment> parentComments = commentAdaptor.findParentCommentsByPostId(postId);
        List<Comment> childComments = commentAdaptor.findChildCommentsByPostId(postId);

        List<ParentCommentData> parentCommentData = parentComments.stream().map(i -> ParentCommentData.of(i, commentLikeAdaptor.existsByCommentIdAndUserId(i.getId(), loginUserId))).toList();
        List<ChildCommentData> childCommentData = childComments.stream().map(i -> ChildCommentData.of(i, commentLikeAdaptor.existsByCommentIdAndUserId(i.getId(), loginUserId))).toList();

        return new CommentData(authorId, parentCommentData, childCommentData);
    }


    public SimpleCommentData createParentComment(CreateParentCommentCommand request) {
        Comment parentComment = parentCommentBy(request);
        Long commentId = commentAdaptor.save(parentComment);
        Long postId = parentComment.getPost().getId();
        return SimpleCommentData.of(commentId, postId);
    }


    public SimpleCommentData createChildComment(CreateChildCommentCommand request) {
        Comment childComment = childCommentBy(request);
        Long commentId = commentAdaptor.save(childComment);
        Long postId = childComment.getPost().getId();

        return SimpleCommentData.of(commentId, postId);
    }


    public CommentIdData updateCommentBody(UpdateCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long updatedId = findComment.updateBody(request.getBody());
            return CommentIdData.of(updatedId);
        }
        throw new NotAuthorizedException();
    }

    public SimpleCommentData deleteCommentSoft(DeleteCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long postId = findComment.getPost().getId();
            Long deletedId = findComment.softDelete();
            return SimpleCommentData.of(deletedId, postId);
        }
        throw new NotAuthorizedException();
    }

    public void deleteCommentHard(DeleteCommentCommand request) {
        Comment findComment = commentAdaptor.findById(request.getCommentId());
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
        comment.setParent(parentCommentBy(request.getParentCommentId()));
        return comment;
    }

    private Post getPostFromParentComment(CreateChildCommentCommand request) {
        Long parentCommentId = request.getParentCommentId();
        Post post = commentAdaptor.findById(parentCommentId).getPost();
        return post;
    }


    private Comment parentCommentBy(Long parentCommentId) {
        return commentAdaptor.findById(parentCommentId);
    }

    private boolean isAuthorized(UpdateCommentCommand request) {
        Long commentId = request.getCommentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.getLoginUserId();
        return authorId.equals(loginUserId);
    }

    private boolean isAuthorized(DeleteCommentCommand request) {
        Long commentId = request.getCommentId();
        Long authorId = getAuthorId(commentId);
        Long loginUserId = request.getLoginUserId();
        return authorId.equals(loginUserId);
    }

    private Long getAuthorId(Long commentId) {
        return commentAdaptor.findById(commentId).getAuthor().getId();
    }

}
