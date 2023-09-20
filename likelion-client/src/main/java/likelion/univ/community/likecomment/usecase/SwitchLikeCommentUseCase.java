package likelion.univ.community.likecomment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.likecomment.dto.LikeCommentRequestDto;
import likelion.univ.domain.community.likecomment.dto.LikeCommentServiceDto;
import likelion.univ.domain.community.likecomment.service.LikeCommentDomainService;
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
