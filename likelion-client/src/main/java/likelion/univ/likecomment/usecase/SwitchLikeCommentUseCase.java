package likelion.univ.likecomment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.likecomment.dto.LikeCommentRequestDto;
import likelion.univ.domain.likecomment.dto.LikeCommentServiceDto;
import likelion.univ.domain.likecomment.service.LikeCommentDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SwitchLikeCommentUseCase {
    private final LikeCommentDomainService likeCommentDomainService;

    public LikeCommentServiceDto.CommandResponse execute(LikeCommentRequestDto.Switch switchRequest, Long likeCommentId) {
        LikeCommentServiceDto.switchLikeCommentRequest switchLikeCommentRequest = buildServiceDtoBy(switchRequest, likeCommentId);
        return likeCommentDomainService.switchLikeComment(switchLikeCommentRequest);
    }

    private LikeCommentServiceDto.switchLikeCommentRequest buildServiceDtoBy(LikeCommentRequestDto.Switch switchRequest, Long likeCommentId) {
        return LikeCommentServiceDto.switchLikeCommentRequest.builder()
                .likeCommentId(likeCommentId)
                .userId(switchRequest.getUserId())
                .build();
    }
}
