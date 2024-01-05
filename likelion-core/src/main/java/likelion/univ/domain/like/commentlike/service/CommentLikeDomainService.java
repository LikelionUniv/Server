package likelion.univ.domain.like.commentlike.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.commentlike.adaptor.CommentLikeAdaptor;
import likelion.univ.domain.like.commentlike.dto.request.CommentLikeCommand;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeDomainService {
    private final UserAdaptor userAdaptor;
    private final CommentAdaptor commentAdaptor;
    private final CommentLikeAdaptor commentLikeAdaptor;

    public boolean createOrDeleteCommentLike(CommentLikeCommand request) throws NotAuthorizedException {
        Long commentId = request.commentId();
        Long loginUserId = request.loginUserId();
//        Comment comment = commentAdaptor.findById(commentId);
//        User user = userAdaptor.findById(loginUserId);

        // 이미 좋아요 했으면 취소
        if (existsCommentLike(commentId, loginUserId)) {
            CommentLike commentLike = commentLikeAdaptor.findByCommentIdAndUserId(commentId, loginUserId);
            if (isAuthorized(commentLike.getUser().getId(), loginUserId)) {
                commentLikeAdaptor.delete(commentLike);
                return false;
            }
            throw new NotAuthorizedException();
        }
        // 좋아요 없으면 새로 생성
        CommentLike newLike = getCommentLike(request);
        commentLikeAdaptor.save(newLike);
        return true;
    }

    /* ----- 내부 편의 메서드 ------ */

    private Boolean existsCommentLike(Long commentId, Long userId) {
        return commentLikeAdaptor.existsByCommentIdAndUserId(commentId, userId);
    }

    private CommentLike getCommentLike(CommentLikeCommand request) {
        return CommentLike.builder()
                .user(userAdaptor.findById(request.loginUserId()))
                .comment(commentAdaptor.findById(request.commentId()))
                .build();
    }

    private boolean isAuthorized(Long commentLikeUserId, Long loginUserId) {
        return commentLikeUserId.equals(loginUserId);
    }
}
