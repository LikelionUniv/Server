package likelion.univ.like.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
import likelion.univ.domain.like.postlike.service.PostLikeDomainService;
import likelion.univ.like.postlike.dto.PostLikeRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateOrDeletePostLikeUseCase {
    private final PostLikeDomainService postLikeDomainService;
    private final AuthenticatedUserUtils userUtils;

    public boolean execute(PostLikeRequestDto request) {
        return postLikeDomainService.createOrDeletePostLike(getServiceDto(request));
    }

    private PostLikeCommand getServiceDto(PostLikeRequestDto request) {
        return new PostLikeCommand(request.postId(), userUtils.getCurrentUserId());
    }


}
