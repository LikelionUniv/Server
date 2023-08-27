package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.CUDResponse execute(CommentRequestDto.CreateParent createRequestDto) {
        CommentServiceDto.CreateParentComment createServiceDto = buildServiceDtoFrom(createRequestDto);
        return commentDomainService.createParentComment(createServiceDto);
    }

    private CommentServiceDto.CreateParentComment buildServiceDtoFrom(CommentRequestDto.CreateParent createParentRequest) {
        return CommentServiceDto.CreateChildComment.builder()
                .post(postAdaptor.findById(createParentRequest.getPostId()))
                .user(userAdaptor.findById(createParentRequest.getUserId()))
                .body(createParentRequest.getBody())
                .build();
    }
}
