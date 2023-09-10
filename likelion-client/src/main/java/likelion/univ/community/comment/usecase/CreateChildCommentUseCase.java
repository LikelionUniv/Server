package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import likelion.univ.domain.community.post.adaptor.PostAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChildCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.CommandResponse execute(CommentRequestDto.CreateChild createRequest) {
        CommentServiceDto.CreateChildCommentRequest createServiceDto = buildServiceDtoBy(createRequest);
        return commentDomainService.createChildComment(createServiceDto);
    }
    private CommentServiceDto.CreateChildCommentRequest buildServiceDtoBy(CommentRequestDto.CreateChild createRequest) {
        return CommentServiceDto.CreateChildCommentRequest.builder()
                .parent(commentAdaptor.findById(createRequest.getParentId()))
                .post(postAdaptor.findById(createRequest.getPostId()))
                .user(userAdaptor.findById(createRequest.getUserId()))
                .body(createRequest.getBody())
                .build();
    }
}
