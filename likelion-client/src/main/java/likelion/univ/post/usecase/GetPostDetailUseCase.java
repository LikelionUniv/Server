package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetPostDetailCommand;
import likelion.univ.domain.post.dto.response.PostDetailData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostDetailResponseDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPostDetailUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public PostDetailResponseDto execute(Long postId) {
        Long loginUserId = authenticatedUserUtils.getCurrentUserId();
        GetPostDetailCommand serviceRequestDto = new GetPostDetailCommand(postId, loginUserId);

        PostDetailData serviceResponseDto = postDomainService.getPostDetail(serviceRequestDto);
        return new PostDetailResponseDto(serviceResponseDto, loginUserId);
    }
}
