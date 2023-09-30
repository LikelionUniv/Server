package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.CommentFindRequestDto;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.CommentDetailResponseDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;

    public SuccessResponse<?> execute(CommentFindRequestDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        List<Comment> comments = commentAdaptor.findAllByPost(post);
        List<CommentDetailResponseDto> response = comments.stream()
                .map(CommentDetailResponseDto::of)
                .toList();
        return SuccessResponse.of(response);
    }

}
