package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.CreatePostCommand;
import likelion.univ.domain.post.dto.response.PostIdData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.request.PostCreateRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePostUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;
    public PostIdData execute(PostCreateRequestDto request) {
        return postDomainService.createPost(serviceDtoBy(request));
    }

    CreatePostCommand serviceDtoBy(PostCreateRequestDto request) {
        return CreatePostCommand.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .authorId(userUtils.getCurrentUserId())
                .thumbnail(request.getThumbnail())
                .mainCategory(request.getMainCategory())
                .subCategory(request.getSubCategory())
                .build();
    }
}
