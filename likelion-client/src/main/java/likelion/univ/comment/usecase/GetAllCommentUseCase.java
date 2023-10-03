package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.CommentServiceDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;

    public List<CommentServiceDto.ReadResponse> execute(Long postId) {
        Post post = postAdaptor.findById(postId);
        List<Comment> allByPost = commentAdaptor.findAllByPost(post);
        return allByPost.stream()
                .map(CommentServiceDto.ReadResponse::of)
                .toList();

    }

}
