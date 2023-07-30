package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.dto.common.CommonResponseDto;

public interface CommentService {
    public CommonResponseDto<Object> createParentComment(CommentDto.CreateParent request);

    public CommonResponseDto<Object> createChildComment(CommentDto.CreateChild request);

    public CommonResponseDto<Object> updateCommentBody(Long commentId, CommentDto.UpdateComment request);

    public CommonResponseDto<Object> deleteComment(Long commentId, CommentDto.DeleteComment request);
}
