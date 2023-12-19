package likelion.univ.domain.comment.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.request.*;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.CommentData;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
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

    public CommentData getComment(Long postId) {
        Long authorId = postAdaptor.findById(postId).getAuthor().getId();

        // comment entity data
        List<ParentCommentData> parentComments = commentAdaptor.findParentCommentsByPostId(postId);
        List<ChildCommentData> childComments = commentAdaptor.findChildCommentsByPostId(postId);

        return new CommentData(authorId, parentComments, childComments);
    }



    public CommentIdData createParentComment(CreateParentCommentCommand request) {
        Comment parentComment = parentCommentBy(request);
        Long savedId = commentAdaptor.save(parentComment);
        return CommentIdData.of(savedId);
    }


    public CommentIdData createChildComment(CreateChildCommentCommand request) {
        Comment childComment = childCommentBy(request);
        Long savedId = commentAdaptor.save(childComment);
        return CommentIdData.of(savedId);
    }


    public CommentIdData updateCommentBody(UpdateCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long updatedId = findComment.updateBody(request.getBody());
            return CommentIdData.of(updatedId);
        }
        throw new NotAuthorizedException();
    }

    public CommentIdData deleteCommentSoft(DeleteCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentAdaptor.findById(request.getCommentId());
            Long deletedId = findComment.softDelete();
            return CommentIdData.of(deletedId);
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
