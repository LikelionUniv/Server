package likelion.univ.community.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.domain.community.comment.service.CommentDomainService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;
    private final CommentDomainService commentDomainService;

    public CommentServiceDto.Response execute(CommentRequestDto.CreateParent createRequestDto) {
        CommentServiceDto.CreateChildComment createServiceDto = buildServiceDtoFrom(createRequestDto);
        return commentDomainService.createParentComment(createServiceDto);
    }

    private CommentServiceDto.CreateChildComment buildServiceDtoFrom(CommentRequestDto.CreateParent createParentRequest) {
        return CommentServiceDto.CreateChildComment.builder()
                .post(postAdaptor.findById(createParentRequest.getPostId()))
                .user(userAdaptor.findById(createParentRequest.getUserId()))
                .body(createParentRequest.getBody())
                .build();
    }
}
