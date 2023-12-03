package likelion.univ.domain.comment.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.comment.dto.ChildCommentDetailResponseDto;
import likelion.univ.domain.comment.dto.CommentDetailResponseDto;
import likelion.univ.domain.comment.dto.ParentCommentDetailResponseDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.exception.CommentNotFoundException;
import likelion.univ.domain.comment.repository.CommentRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;


@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Long save(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public List<CommentDetailResponseDto> findCommentsByPostId(Long postId, Long loginUserId) {
        return commentRepository.findCommentsByPostId(postId, loginUserId);
    }

    public Long countByPostId(Long postId){
        return commentRepository.countByPostId(postId);
    }
}
