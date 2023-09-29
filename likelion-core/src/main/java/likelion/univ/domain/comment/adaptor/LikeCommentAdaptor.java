package likelion.univ.domain.comment.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.comment.entity.LikeComment;
import likelion.univ.domain.comment.exception.LikeCommentNotFoundException;
import likelion.univ.domain.comment.repository.LikeCommentRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class LikeCommentAdaptor {
    private final LikeCommentRepository likeCommentRepository;

    public LikeComment save(LikeComment likeComment) {
        return likeCommentRepository.save(likeComment);
    }

    public LikeComment findById(Long id) {
        return likeCommentRepository.findById(id)
                .orElseThrow(LikeCommentNotFoundException::new);
    }
    public void delete(LikeComment likeComment) {
        likeCommentRepository.delete(likeComment);
    }

    public List<LikeComment> findAllByUser(User user) {
        return likeCommentRepository.findByUser(user);
    }

}
