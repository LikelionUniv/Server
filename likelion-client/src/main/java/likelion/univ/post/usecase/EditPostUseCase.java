package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.response.PostIdResponseDto;
import likelion.univ.domain.post.dto.request.UpdatePostServiceDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class EditPostUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;

    public PostIdResponseDto execute(Long postId, PostUpdateRequestDto request) {
        return postDomainService.editPost(buildDTO(postId, request));
    }

    private UpdatePostServiceDto buildDTO(Long postId, PostUpdateRequestDto request) {
        return UpdatePostServiceDto.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .postId(postId)
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .body(request.getBody())
                .build();
    }
}
