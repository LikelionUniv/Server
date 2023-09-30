package likelion.univ.likepost.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.likepost.dto.LikePostDeleteServiceDto;
import likelion.univ.domain.likepost.service.LikePostDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.likepost.dto.LikePostRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteLikePostUseCase {

    private final LikePostDomainService likePostDomainService;
    private final AuthentiatedUserUtils userUtils;
    private final PostAdaptor postAdaptor;

    public void execute(LikePostRequestDto postIdDto) {
        likePostDomainService.deleteLikePost(requestDtoBy(postIdDto));

    }

    private LikePostDeleteServiceDto requestDtoBy(LikePostRequestDto postIdDto) {
        return LikePostDeleteServiceDto.builder()
                .post(postAdaptor.findById(postIdDto.getPostId()))
                .author(userUtils.getCurrentUser())
                .build();
    }
}
