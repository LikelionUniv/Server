package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentDto.Save createComment(CommentDto.Save createRequest, Long postId, Long userId) {
        // 포스트 아디이를 가이 호괴
        return null;
    }

    @Override
    public void updateComment(CommentDto.Save updateRequest, Long postId, Long userId) {

    }

    @Override
    public void deleteComment(CommentDto.Delete deleteRequest, Long postId, Long userId) {

    }
}
