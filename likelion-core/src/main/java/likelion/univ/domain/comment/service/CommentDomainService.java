package likelion.univ.domain.comment.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.dto.CommentServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDomainService {
    private final CommentAdaptor commentAdaptor;

    public CommentServiceDto.CommandResponse createParentComment(CommentServiceDto.CreateParentCommentRequest createParentCommentRequest) {
        Comment parent = buildParentComment(createParentCommentRequest);
        Comment saveComment = commentAdaptor.save(parent);

        return CommentServiceDto.CommandResponse.of(saveComment);
    }


    public CommentServiceDto.CommandResponse createChildComment(CommentServiceDto.CreateChildCommentRequest createChildCommentRequest) {
        Comment child = buildChildComment(createChildCommentRequest);
        Comment saveComment = commentAdaptor.save(child);
        return CommentServiceDto.CommandResponse.of(saveComment);
    }


    public CommentServiceDto.CommandResponse editCommentBody(CommentServiceDto.EditCommentRequest editCommentRequest) {
        Comment findComment = commentAdaptor.findById(editCommentRequest.getId());
        Comment editComment = findComment.editBody(editCommentRequest.getBody());
        return CommentServiceDto.CommandResponse.of(editComment);
    }

    public CommentServiceDto.CommandResponse deleteCommentSoft(CommentServiceDto.DeleteCommentRequest deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        Comment deleteComment = findComment.delete();
        return CommentServiceDto.CommandResponse.of(deleteComment);
    }

    public void deleteCommentHard(CommentServiceDto.DeleteCommentRequest deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        commentAdaptor.delete(findComment);
    }

    /* --------------- 내부 편의 메서드 --------------- */
    private static Comment buildParentComment(CommentServiceDto.CreateParentCommentRequest createParentCommentRequest) {
        return Comment.builder()
                .post(createParentCommentRequest.getPost())
                .author(createParentCommentRequest.getUser())
                .body(createParentCommentRequest.getBody())
                .build();
    }
    private static Comment buildChildComment(CommentServiceDto.CreateChildCommentRequest createChildCommentRequest) {
        return Comment.builder()
                .post(createChildCommentRequest.getPost())
                .author(createChildCommentRequest.getUser())
                .body(createChildCommentRequest.getBody())
                .build();
    }

}
