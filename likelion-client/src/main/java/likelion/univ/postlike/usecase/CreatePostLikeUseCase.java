package likelion.univ.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.postlike.dto.PostLikeCreateServiceDto;
import likelion.univ.domain.postlike.dto.PostLikeResponseDto;
import likelion.univ.domain.postlike.service.PostLikeDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.postlike.dto.PostLikeRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePostLikeUseCase {
    private final PostLikeDomainService postLikeDomainService;
    private final AuthentiatedUserUtils userUtils;
    private final PostAdaptor postAdaptor;

    public PostLikeResponseDto execute(PostLikeRequestDto postIdDto) {
        return postLikeDomainService.createLikePost(serviceDtoBy(postIdDto));
    }

    private PostLikeCreateServiceDto serviceDtoBy(PostLikeRequestDto postIdDto) {
        return PostLikeCreateServiceDto.builder()
                .post(postAdaptor.findById(postIdDto.getPostId()))
                .author(userUtils.getCurrentUser())
                .build();
    }


}
