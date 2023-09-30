package likelion.univ.domain.commentlike.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.commentlike.adaptor.CommentLikeAdaptor;
import likelion.univ.domain.commentlike.dto.CommentLikeServiceDto;
import likelion.univ.domain.commentlike.entity.CommentLike;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeDomainService {
    private final CommentLikeAdaptor commentLikeAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentAdaptor commentAdaptor;

    // 좋아요 생성
    public CommentLikeServiceDto.CommandResponse createLikeComment(CommentLikeServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        CommentLike commentLike = getLikeCommentBy(createLikeCommentRequest);
        CommentLike saveCommentLike = commentLikeAdaptor.save(commentLike);
        return CommentLikeServiceDto.CommandResponse.of(saveCommentLike);
    }

    // 좋아요 전환
    public CommentLikeServiceDto.CommandResponse switchLikeComment(CommentLikeServiceDto.switchLikeCommentRequest switchLikeCommentRequest) {
        CommentLike findCommentLike = commentLikeAdaptor.findById(switchLikeCommentRequest.getLikeCommentId());
        if (isSameUser(switchLikeCommentRequest, findCommentLike)) {
            throw new NotAuthorizedException();
        }
        CommentLike switchedCommentLike = findCommentLike.switchLikeComment();
        return CommentLikeServiceDto.CommandResponse.of(switchedCommentLike);
    }


    /* ----- 내부 편의 메서드 ------ */
    private CommentLike getLikeCommentBy(CommentLikeServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return CommentLike.builder()
                .user(getUserBy(createLikeCommentRequest))
                .comment(getCommentBy(createLikeCommentRequest))
                .build();
    }
    private boolean isSameUser(CommentLikeServiceDto.switchLikeCommentRequest switchLikeCommentRequest, CommentLike commentLike) {
        return commentLike.getUser().equals(userAdaptor.findById(switchLikeCommentRequest.getUserId()));
    }
    private User getUserBy(CommentLikeServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return userAdaptor.findById(createLikeCommentRequest.getUserId());
    }
    private Comment getCommentBy(CommentLikeServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return commentAdaptor.findById(createLikeCommentRequest.getCommentId());
    }

}
