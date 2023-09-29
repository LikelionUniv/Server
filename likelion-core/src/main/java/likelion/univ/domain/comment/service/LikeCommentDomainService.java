package likelion.univ.domain.comment.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.AuthorNotDetectedException;
import likelion.univ.domain.comment.adaptor.LikeCommentAdaptor;
import likelion.univ.domain.comment.dto.LikeCommentServiceDto;
import likelion.univ.domain.comment.entity.LikeComment;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeCommentDomainService {
    private final LikeCommentAdaptor likeCommentAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentAdaptor commentAdaptor;

    // 좋아요 생성
    public LikeCommentServiceDto.CommandResponse createLikeComment(LikeCommentServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        LikeComment likeComment = getLikeCommentBy(createLikeCommentRequest);
        LikeComment saveLikeComment = likeCommentAdaptor.save(likeComment);
        return LikeCommentServiceDto.CommandResponse.of(saveLikeComment);
    }

    // 좋아요 전환
    public LikeCommentServiceDto.CommandResponse switchLikeComment(LikeCommentServiceDto.switchLikeCommentRequest switchLikeCommentRequest) {
        LikeComment findLikeComment = likeCommentAdaptor.findById(switchLikeCommentRequest.getLikeCommentId());
        if (isSameUser(switchLikeCommentRequest, findLikeComment)) {
            throw new AuthorNotDetectedException();
        }
        LikeComment switchedLikeComment = findLikeComment.switchLikeComment();
        return LikeCommentServiceDto.CommandResponse.of(switchedLikeComment);
    }


    /* ----- 내부 편의 메서드 ------ */
    private LikeComment getLikeCommentBy(LikeCommentServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return LikeComment.builder()
                .user(getUserBy(createLikeCommentRequest))
                .comment(getCommentBy(createLikeCommentRequest))
                .build();
    }
    private boolean isSameUser(LikeCommentServiceDto.switchLikeCommentRequest switchLikeCommentRequest, LikeComment likeComment) {
        return likeComment.getUser().equals(userAdaptor.findById(switchLikeCommentRequest.getUserId()));
    }
    private User getUserBy(LikeCommentServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return userAdaptor.findById(createLikeCommentRequest.getUserId());
    }
    private Comment getCommentBy(LikeCommentServiceDto.createLikeCommentRequest createLikeCommentRequest) {
        return commentAdaptor.findById(createLikeCommentRequest.getCommentId());
    }

}
