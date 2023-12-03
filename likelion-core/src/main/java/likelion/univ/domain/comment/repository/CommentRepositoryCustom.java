package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.dto.CommentDetailResponseDto;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentDetailResponseDto> findCommentsByPostId(Long postId, Long loginUserId);
}
