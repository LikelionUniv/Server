package likelion.univ.domain.community.comment.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.comment.exception.CommentNotFoundException;
import likelion.univ.domain.community.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;


@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException());
    }
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment find

}
