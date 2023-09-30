package likelion.univ.likepost.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.likepost.dto.LikePostCreateRequestDto;
import likelion.univ.domain.likepost.dto.LikePostResponseDto;
import likelion.univ.domain.likepost.service.LikePostDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.likepost.dto.LikePostRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateLikePostUseCase {
    private final LikePostDomainService likePostDomainService;
    private final AuthentiatedUserUtils userUtils;
    private final PostAdaptor postAdaptor;

    public LikePostResponseDto execute(LikePostRequestDto postIdDto) {
        return likePostDomainService.createLikePost(requestDtoBy(postIdDto));
    }

    private LikePostCreateRequestDto requestDtoBy(LikePostRequestDto postIdDto) {
        return LikePostCreateRequestDto.builder()
                .post(postAdaptor.findById(postIdDto.getPostId()))
                .author(userUtils.getCurrentUser())
                .build();
    }


}
