package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetPostDetailServiceDto;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPostDetailUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public PostDetailResponseDto execute(Long postId) {
        Long loginUserId = authenticatedUserUtils.getCurrentUserId();
        GetPostDetailServiceDto serviceDto = new GetPostDetailServiceDto(postId, loginUserId);

        return postDomainService.getPostDetail(serviceDto);
    }
}
