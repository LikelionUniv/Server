package likelion.univ.domain.community.comment.service;

import likelion.univ.domain.community.comment.dto.CommentRequestDto;
import likelion.univ.response.SuccessResponse;

public interface CommentService {
    public SuccessResponse<Object> createParentComment(CommentRequestDto.CreateParent createParent);

    public SuccessResponse<Object> createChildComment(CommentRequestDto.CreateChild createChild);

    public SuccessResponse<Object> editCommentBody(Long commentId, CommentRequestDto.EditComment editComment);

    public SuccessResponse<Object> deleteComment(Long commentId, CommentRequestDto.DeleteComment deleteComment);
}
