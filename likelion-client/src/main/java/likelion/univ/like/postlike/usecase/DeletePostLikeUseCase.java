package likelion.univ.like.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.postlike.dto.PostLikeDeleteServiceDto;
import likelion.univ.domain.like.postlike.service.PostLikeDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeletePostLikeUseCase {

    private final PostLikeDomainService postLikeDomainService;
    private final AuthentiatedUserUtils userUtils;
    private final PostAdaptor postAdaptor;

    public SuccessResponse<?> execute(Long postLikeId) {
        PostLikeDeleteServiceDto serviceDto = serviceDtoBy(postLikeId);
        postLikeDomainService.deleteLikePost(serviceDto);
        return SuccessResponse.empty();
    }

    private PostLikeDeleteServiceDto serviceDtoBy(Long postLikeId) {
        return PostLikeDeleteServiceDto.builder()
                .postLikeId(postLikeId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
