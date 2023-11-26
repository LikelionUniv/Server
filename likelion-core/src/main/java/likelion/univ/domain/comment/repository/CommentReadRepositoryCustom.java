package likelion.univ.domain.comment.repository;

import likelion.univ.domain.comment.dto.CommentDetailResponseDto;

import java.util.List;

public interface CommentReadRepositoryCustom {
    List<CommentDetailResponseDto> findAll(Long postId);
}
