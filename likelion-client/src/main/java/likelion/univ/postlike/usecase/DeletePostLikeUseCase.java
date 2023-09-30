package likelion.univ.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.postlike.dto.PostLikeDeleteServiceDto;
import likelion.univ.domain.postlike.service.PostLikeDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.postlike.dto.PostLikeRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeletePostLikeUseCase {

    private final PostLikeDomainService postLikeDomainService;
    private final AuthentiatedUserUtils userUtils;
    private final PostAdaptor postAdaptor;

    public void execute(PostLikeRequestDto postIdDto) {
        postLikeDomainService.deleteLikePost(requestDtoBy(postIdDto));

    }

    private PostLikeDeleteServiceDto requestDtoBy(PostLikeRequestDto postIdDto) {
        return PostLikeDeleteServiceDto.builder()
                .post(postAdaptor.findById(postIdDto.getPostId()))
                .author(userUtils.getCurrentUser())
                .build();
    }
}
