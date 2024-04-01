package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetPostDetailCommand;
import likelion.univ.domain.post.dto.response.PostDetailData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostDetailResponseDto;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPostDetailUsecase {

    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PostDetailResponseDto execute(Long postId) {
        Long loginUserId = authenticatedUserUtils.getCurrentUserId();
        GetPostDetailCommand serviceRequestDto = new GetPostDetailCommand(postId, loginUserId);

        PostDetailData serviceResponseDto = postDomainService.getPostDetail(serviceRequestDto);
        PostDetailResponseDto response = PostDetailResponseDto.of(serviceResponseDto,
                getOrCreatePostCountInfoProcessor.execute(serviceResponseDto.postId()), loginUserId);
        return response;
    }
}
