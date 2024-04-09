package likelion.univ.domain.like.commentlike.service;

import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.comment.repository.CommentRepository;
import likelion.univ.domain.like.commentlike.dto.request.CommentLikeCommand;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.like.commentlike.repository.CommentLikeRepository;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeDomainService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public boolean createOrDeleteCommentLike(CommentLikeCommand request) throws NotAuthorizedException {
        Long commentId = request.commentId();
        Long loginUserId = request.loginUserId();

        // 이미 좋아요 했으면 취소
        if (existsCommentLike(commentId, loginUserId)) {
            CommentLike commentLike = commentLikeRepository.getByCommentIdAndUserId(commentId, loginUserId);
            if (isAuthorized(commentLike.getUser().getId(), loginUserId)) {
                commentLikeRepository.delete(commentLike);
                return false;
            }
            throw new NotAuthorizedException();
        }
        // 좋아요 없으면 새로 생성
        CommentLike newLike = getCommentLike(request);
        commentLikeRepository.save(newLike);
        return true;
    }

    /* ----- 내부 편의 메서드 ------ */

    private Boolean existsCommentLike(Long commentId, Long userId) {
        return commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
    }

    private CommentLike getCommentLike(CommentLikeCommand request) {
        return CommentLike.builder()
                .user(userRepository.getById(request.loginUserId()))
                .comment(commentRepository.getById(request.commentId()))
                .build();
    }

    private boolean isAuthorized(Long commentLikeUserId, Long loginUserId) {
        return commentLikeUserId.equals(loginUserId);
    }
}
