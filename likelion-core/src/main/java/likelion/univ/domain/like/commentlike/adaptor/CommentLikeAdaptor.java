package likelion.univ.domain.like.commentlike.adaptor;

import java.util.List;
import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.like.commentlike.exception.CommentLikeNotFoundException;
import likelion.univ.domain.like.commentlike.repository.CommentLikeRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class CommentLikeAdaptor {
    private final CommentLikeRepository commentLikeRepository;

    public Long save(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike).getId();
    }

    public CommentLike findById(Long id) {
        return commentLikeRepository.findById(id)
                .orElseThrow(CommentLikeNotFoundException::new);
    }

    public void delete(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
    }

    public List<CommentLike> findAllByUser(User user) {
        return commentLikeRepository.findByUser(user);
    }

    public CommentLike findByCommentIdAndUserId(Long commentId, Long userId) {
        return commentLikeRepository.findByCommentIdAndUserId(commentId, userId)
                .orElseThrow(() -> new CommentLikeNotFoundException());
    }

    public Boolean existsByCommentIdAndUserId(Long commentId, Long userId) {
        return commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
    }

}
