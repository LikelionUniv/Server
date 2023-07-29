package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;

public interface CommentService {
    public CommentDto.ResponseSave createParentComment(CommentDto.RequestSave createRequest, Long postId, Long userId);

    public CommentDto.ResponseSave createChildComment(CommentDto.RequestSave createRequest, Long postId, Long userId, Long parentId);

    public CommentDto.ResponseSave updateCommentBody(CommentDto.RequestSave updateRequest, Long commentId, Long userId);

}
