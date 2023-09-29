package likelion.univ.likecomment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.likecomment.dto.LikeCommentRequestDto;
import likelion.univ.domain.likecomment.dto.LikeCommentServiceDto;
import likelion.univ.domain.likecomment.service.LikeCommentDomainService;
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
