package likelion.univ.domain.like.commentlike.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.commentlike.adaptor.CommentLikeAdaptor;
import likelion.univ.domain.like.commentlike.dto.CommentLikeCreateServiceDto;
import likelion.univ.domain.like.commentlike.dto.CommentLikeResponseDto;
import likelion.univ.domain.like.commentlike.dto.CommentLikeSwitchServiceDto;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.user.adaptor.UserAdaptor;
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

    // 좋아요 생성
    public CommentLikeResponseDto createLikeComment(CommentLikeCreateServiceDto request) {
        CommentLike commentLike = getCommentLikeBy(request);
        Long savedId = commentLikeAdaptor.save(commentLike);
        return CommentLikeResponseDto.of(savedId);
    }

    // 좋아요 전환
    public CommentLikeResponseDto switchLikeComment(CommentLikeSwitchServiceDto request) {
        if (isAuthorized(request)) {
            CommentLike commentLike = commentLikeAdaptor.findById(request.getCommentLikeId());
            CommentLike switchedCommentLike = commentLike.switchLikeComment();
            return CommentLikeResponseDto.of(switchedCommentLike);
        }
        throw new NotAuthorizedException();
    }


    /* ----- 내부 편의 메서드 ------ */
    private CommentLike getCommentLikeBy(CommentLikeCreateServiceDto request) {
        return CommentLike.builder()
                .user(userAdaptor.findById(request.getLoginUserId()))
                .comment(commentAdaptor.findById(request.getCommentId()))
                .isCanceled(false) // default
                .build();
    }
    private boolean isAuthorized(CommentLikeSwitchServiceDto request) {
        Long commentLikeId = request.getCommentLikeId();
        Long commentLikeAuthorId = commentLikeAdaptor.findById(commentLikeId).getUser().getId();
        Long loginUserId = request.getLoginUserId();

        return commentLikeAuthorId.equals(loginUserId);
    }
}
