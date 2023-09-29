package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.post.adaptor.PostAdaptor;
import likelion.univ.domain.community.post.entity.Post;
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
