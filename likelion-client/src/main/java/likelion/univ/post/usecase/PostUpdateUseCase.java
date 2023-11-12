package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.dto.request.PostUpdateServiceDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class PostUpdateUseCase {
    private final PostDomainService postDomainService;
    private final AuthentiatedUserUtils userUtils;

    public PostCommandResponseDto execute(Long postId, PostUpdateRequestDto request) {
        return postDomainService.editPost(buildDTO(postId, request));
    }

    private PostUpdateServiceDto buildDTO(Long postId, PostUpdateRequestDto request) {
        return PostUpdateServiceDto.builder()
                .loginUserId(userUtils.getCurrentUserId())
                .postId(postId)
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .body(request.getBody())
                .build();
    }
}
