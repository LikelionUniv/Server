package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.PostCreateServiceDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PostCreateUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;
    public PostCommandResponseDto execute(PostCreateRequestDto request) {
        return postDomainService.createPost(serviceDtoBy(request));
    }

    PostCreateServiceDto serviceDtoBy(PostCreateRequestDto request) {
        return PostCreateServiceDto.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .authorId(userUtils.getCurrentUserId())
                .thumbnail(request.getThumbnail())
                .mainCategory(request.getMainCategory())
                .subCategory(request.getSubCategory())
                .build();
    }
}
