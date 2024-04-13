package likelion.univ.like.commentlike.service;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.commentlike.dto.request.CommentLikeCommand;
import likelion.univ.domain.like.commentlike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ClientCommentLikeService {

    private final CommentLikeService commentLikeService;

    public boolean createOrDeleteCommentLike(CommentLikeCommand command) throws NotAuthorizedException {
        return commentLikeService.createOrDeleteCommentLike(command);
    }
}
