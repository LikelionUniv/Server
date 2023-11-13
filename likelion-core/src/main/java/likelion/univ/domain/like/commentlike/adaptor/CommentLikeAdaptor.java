package likelion.univ.domain.like.commentlike.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.like.commentlike.exception.CommentLikeNotFoundException;
import likelion.univ.domain.like.commentlike.repository.CommentLikeRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

}
