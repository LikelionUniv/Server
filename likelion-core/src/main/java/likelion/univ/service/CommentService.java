package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.entity.Comment;

import java.net.http.HttpResponse;

public interface CommentService {
    public CommentDto.Save createComment(CommentDto.Save createRequest, Long postId, Long userId);

    public void updateComment(CommentDto.Save updateRequest, Long postId, Long userId);

    public void deleteComment(CommentDto.Delete deleteRequest, Long postId, Long userId);
}
