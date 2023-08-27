package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChildCommentUseCase {
    private final CommentAdaptor commentAdaptor;
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.CUDResponse execute(CommentRequestDto.CreateChild createRequestDto) {
        CommentServiceDto.CreateChildComment createServiceDto = buildServiceDtoFrom(createRequestDto);
        return commentDomainService.createChildComment(createServiceDto);
    }
    private CommentServiceDto.CreateChildComment buildServiceDtoFrom(CommentRequestDto.CreateChild createChildRequest) {
        return CommentServiceDto.CreateChildComment.builder()
                .parent(commentAdaptor.findById(createChildRequest.getParentId()))
                .post(postAdaptor.findById(createChildRequest.getPostId()))
                .user(userAdaptor.findById(createChildRequest.getUserId()))
                .body(createChildRequest.getBody())
                .build();
    }
}
