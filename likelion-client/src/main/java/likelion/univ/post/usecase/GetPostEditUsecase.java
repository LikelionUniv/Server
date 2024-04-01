package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.response.PostEditData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostEditResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPostEditUsecase {
    private final PostDomainService postDomainService;

    public PostEditResponseDto execute(Long postId) {
        PostEditData postEdit = postDomainService.getPostEditById(postId);
        PostEditResponseDto response = PostEditResponseDto.of(postEdit);
        return response;
    }
}
