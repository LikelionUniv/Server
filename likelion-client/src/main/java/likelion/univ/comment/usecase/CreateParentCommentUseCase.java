package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.CommentRequestDto;
import likelion.univ.domain.comment.dto.CommentServiceDto;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.CommandResponse execute(CommentRequestDto.CreateParent createRequestDto) {
        CommentServiceDto.CreateParentCommentRequest createServiceDto = buildServiceDtoBy(createRequestDto);
        return commentDomainService.createParentComment(createServiceDto);
    }

    private CommentServiceDto.CreateParentCommentRequest buildServiceDtoBy(CommentRequestDto.CreateParent createParentRequest) {
        return CommentServiceDto.CreateParentCommentRequest.builder()
                .post(postAdaptor.findById(createParentRequest.getPostId()))
                .user(userAdaptor.findById(createParentRequest.getUserId()))
                .body(createParentRequest.getBody())
                .build();
    }
}
