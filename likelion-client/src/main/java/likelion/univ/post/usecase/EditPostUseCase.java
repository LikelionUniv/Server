package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.response.PostIdData;
import likelion.univ.domain.post.dto.request.UpdatePostCommand;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.request.PostUpdateRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class EditPostUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;

    public PostIdData execute(Long postId, PostUpdateRequestDto request) {
        return postDomainService.editPost(buildDTO(postId, request));
    }

    private UpdatePostCommand buildDTO(Long postId, PostUpdateRequestDto request) {
        return UpdatePostCommand.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .postId(postId)
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .body(request.getBody())
                .build();
    }
}
