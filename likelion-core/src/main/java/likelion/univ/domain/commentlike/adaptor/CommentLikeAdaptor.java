package likelion.univ.domain.commentlike.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.commentlike.entity.CommentLike;
import likelion.univ.domain.commentlike.exception.CommentLikeNotFoundException;
import likelion.univ.domain.commentlike.repository.CommentLikeRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class CommentLikeAdaptor {
    private final CommentLikeRepository commentLikeRepository;

    public CommentLike save(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
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
