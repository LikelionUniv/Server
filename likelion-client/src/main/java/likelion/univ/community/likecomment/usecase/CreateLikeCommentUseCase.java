package likelion.univ.community.likecomment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.likecomment.dto.LikeCommentRequestDto;
import likelion.univ.domain.community.likecomment.dto.LikeCommentServiceDto;
import likelion.univ.domain.community.likecomment.service.LikeCommentDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateLikeCommentUseCase {
    private final LikeCommentDomainService likeCommentDomainService;

    public LikeCommentServiceDto.CommandResponse execute(LikeCommentRequestDto.Create createRequest) {
        LikeCommentServiceDto.createLikeCommentRequest createLikeCommentRequest = buildServiceDtoBy(createRequest);
        return likeCommentDomainService.createLikeComment(createLikeCommentRequest);
    }

    private LikeCommentServiceDto.createLikeCommentRequest buildServiceDtoBy(LikeCommentRequestDto.Create createRequest) {
        return LikeCommentServiceDto.createLikeCommentRequest.builder()
                .userId(createRequest.getUserId())
                .commentId(createRequest.getCommentId())
                .build();
    }

}
