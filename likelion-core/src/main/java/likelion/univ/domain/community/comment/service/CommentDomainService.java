package likelion.univ.domain.community.comment.service;

import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDomainService {
    private final CommentAdaptor commentAdaptor;

    public CommentServiceDto.Response createParentComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        Comment parent = buildParentComment(createChildCommentRequest);
        Comment saveComment = commentAdaptor.save(parent);

        return getResponseDto(saveComment);
    }


    public CommentServiceDto.Response createChildComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        Comment child = buildChildComment(createChildCommentRequest);
        Comment saveComment = commentAdaptor.save(child);
        return getResponseDto(saveComment);
    }


    public CommentServiceDto.Response editCommentBody(CommentServiceDto.EditComment editCommentRequest) {
        Comment findComment = commentAdaptor.findById(editCommentRequest.getId());
        Comment editComment = findComment.editBody(editCommentRequest.getBody());
        return getResponseDto(editComment);
    }

    public CommentServiceDto.Response deleteCommentSoft(CommentServiceDto.DeleteComment deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        Comment deleteComment = findComment.delete();
        return getResponseDto(deleteComment);
    }

    public void deleteCommentHard(CommentServiceDto.DeleteComment deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        commentAdaptor.delete(findComment);
    }

    /* --------------- 내부 편의 메서드 --------------- */
    private static Comment buildParentComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        return Comment.builder()
                .post(createChildCommentRequest.getPost())
                .user(createChildCommentRequest.getUser())
                .body(createChildCommentRequest.getBody())
                .build();
    }
    private static Comment buildChildComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        return Comment.builder()
                .parentComment(createChildCommentRequest.getParent())
                .post(createChildCommentRequest.getPost())
                .user(createChildCommentRequest.getUser())
                .body(createChildCommentRequest.getBody())
                .build();
    }
    private static CommentServiceDto.Response getResponseDto(Comment comment) {
        return CommentServiceDto.Response.builder()
                .id(comment.getId())
                .build();
    }

}
