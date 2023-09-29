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

    public CommentServiceDto.CUDResponse createParentComment(CommentServiceDto.CreateParentComment createParentCommentRequest) {
        Comment parent = buildParentComment(createParentCommentRequest);
        Comment saveComment = commentAdaptor.save(parent);

        return getResponseDto(saveComment);
    }


    public CommentServiceDto.CUDResponse createChildComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        Comment child = buildChildComment(createChildCommentRequest);
        Comment saveComment = commentAdaptor.save(child);
        return getResponseDto(saveComment);
    }


    public CommentServiceDto.CUDResponse editCommentBody(CommentServiceDto.EditComment editCommentRequest) {
        Comment findComment = commentAdaptor.findById(editCommentRequest.getId());
        Comment editComment = findComment.editBody(editCommentRequest.getBody());
        return getResponseDto(editComment);
    }

    public CommentServiceDto.CUDResponse deleteCommentSoft(CommentServiceDto.DeleteComment deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        Comment deleteComment = findComment.delete();
        return getResponseDto(deleteComment);
    }

    public void deleteCommentHard(CommentServiceDto.DeleteComment deleteCommentRequest) {
        Comment findComment = commentAdaptor.findById(deleteCommentRequest.getId());
        commentAdaptor.delete(findComment);
    }

    /* --------------- 내부 편의 메서드 --------------- */
    private static Comment buildParentComment(CommentServiceDto.CreateParentComment createParentCommentRequest) {
        return Comment.builder()
                .post(createParentCommentRequest.getPost())
                .user(createParentCommentRequest.getUser())
                .body(createParentCommentRequest.getBody())
                .build();
    }
    private static Comment buildChildComment(CommentServiceDto.CreateChildComment createChildCommentRequest) {
        return Comment.builder()
                .post(createChildCommentRequest.getPost())
                .user(createChildCommentRequest.getUser())
                .body(createChildCommentRequest.getBody())
                .build();
    }
    private static CommentServiceDto.CUDResponse getResponseDto(Comment comment) {
        return CommentServiceDto.CUDResponse.builder()
                .id(comment.getId())
                .build();
    }

}
