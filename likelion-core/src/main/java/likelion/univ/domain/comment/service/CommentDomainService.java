package likelion.univ.domain.comment.service;

import java.util.List;
import likelion.univ.domain.comment.dto.request.CreateChildCommentCommand;
import likelion.univ.domain.comment.dto.request.CreateParentCommentCommand;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.domain.comment.dto.request.GetCommentCommand;
import likelion.univ.domain.comment.dto.request.UpdateCommentCommand;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.CommentData;
import likelion.univ.domain.comment.dto.response.DeleteCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.comment.repository.CommentRepository;
import likelion.univ.domain.like.commentlike.repository.CommentLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDomainService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentData getComment(GetCommentCommand command) {
        Long postId = command.postId();
        Long loginUserId = command.loginUserId();
        Long authorId = postRepository.getById(postId).getAuthor().getId();

        // comment entity data
        List<Comment> parentComments = commentRepository.findParentCommentsByPostId(postId);
        List<Comment> childComments = commentRepository.findChildCommentsByPostId(postId);

        List<ParentCommentData> parentCommentData = parentComments.stream().map(i -> ParentCommentData.of(i,
                commentLikeRepository.existsByCommentIdAndUserId(i.getId(), loginUserId))).toList();
        List<ChildCommentData> childCommentData = childComments.stream()
                .map(i -> ChildCommentData.of(i,
                        commentLikeRepository.existsByCommentIdAndUserId(i.getId(), loginUserId)))
                .toList();

        return new CommentData(authorId, parentCommentData, childCommentData);
    }

    public void createParentComment(CreateParentCommentCommand request) {
        Comment parentComment = parentCommentBy(request);
        commentRepository.save(parentComment);
    }

    public Long createChildComment(CreateChildCommentCommand request) {
        Comment childComment = childCommentBy(request);
        Long postId = childComment.getPost().getId();
        commentRepository.save(childComment);
        return postId;
    }

    public Long updateCommentBody(UpdateCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentRepository.getById(request.getCommentId());
            Long updatedCommentId = findComment.updateBody(request.getBody());
            return updatedCommentId;
        }
        throw new NotAuthorizedException();
    }

    public DeleteCommentData deleteCommentSoft(DeleteCommentCommand request) {
        if (isAuthorized(request)) {
            Comment findComment = commentRepository.getById(request.commentId());
            Boolean isDeleted = findComment.softDelete();
            Long postId = findComment.getPost().getId();
            return new DeleteCommentData(isDeleted, postId);
        }
        throw new NotAuthorizedException();
    }

    public void deleteCommentHard(DeleteCommentCommand request) {
        Comment findComment = commentRepository.getById(request.commentId());
        commentRepository.delete(findComment);
    }


    /* --------------- 내부 편의 메서드 --------------- */
    private Comment parentCommentBy(CreateParentCommentCommand request) {
        return Comment.builder()
                .post(postRepository.getById(request.postId()))
                .author(userRepository.getById(request.loginUserId()))
                .body(request.body())
                .build();
    }

    private Comment childCommentBy(CreateChildCommentCommand request) {
        Comment comment = Comment.builder()
                .post(getPostFromParentComment(request))
                .author(userRepository.getById(request.getLoginUserId()))
                .body(request.getBody())
                .build();
        comment.setParent(commentRepository.getById(request.getParentCommentId()));
        return comment;
    }

    private Post getPostFromParentComment(CreateChildCommentCommand request) {
        Long parentCommentId = request.getParentCommentId();
        Post post = commentRepository.getById(parentCommentId).getPost();
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
        return commentRepository.getById(commentId).getAuthor().getId();
    }
}
