package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.CreatePostServiceDto;
import likelion.univ.domain.post.dto.response.PostIdResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePostUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;
    public PostIdResponseDto execute(PostCreateRequestDto request) {
        return postDomainService.createPost(serviceDtoBy(request));
    }

    CreatePostServiceDto serviceDtoBy(PostCreateRequestDto request) {
        return CreatePostServiceDto.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .authorId(userUtils.getCurrentUserId())
                .thumbnail(request.getThumbnail())
                .mainCategory(request.getMainCategory())
                .subCategory(request.getSubCategory())
                .build();
    }
}
