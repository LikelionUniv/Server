package likelion.univ.comment.repository;

import likelion.univ.domain.comment.dto.CommentDetailResponseDto;

import java.util.List;

public interface CommentReadRepositoryCustom {
    List<CommentDetailResponseDto> findAll(Long postId);
}
