package likelion.univ.community.likecomment.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.community.likecomment.dto.LikeCommentRequestDto;
import likelion.univ.community.likecomment.usecase.CreateLikeCommentUseCase;
import likelion.univ.community.likecomment.usecase.SwitchLikeCommentUseCase;
import likelion.univ.domain.community.likecomment.dto.LikeCommentServiceDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/likecomment")
@Api(tags = {"댓글 좋아요 API"})
public class LikeCommentApiController {
    private final CreateLikeCommentUseCase createLikeCommentUseCase;
    private final SwitchLikeCommentUseCase switchLikeCommentUseCase;

    @Operation(summary = "댓글 좋아요 수행", description = "댓글 좋아요를 생성합니다.")
    @PostMapping("/new")
    public SuccessResponse<?> createLikeComment(@RequestBody LikeCommentRequestDto.Create createRequest) {
        LikeCommentServiceDto.CommandResponse createLikeCommentResponse = createLikeCommentUseCase.execute(createRequest);
        return SuccessResponse.of(createLikeCommentResponse);
    }

    @Operation(summary = "댓글 좋아요 수정", description = "이미 좋아요한 댓글을 취소하거나, 취소한 댓글을 다시 좋아요 합니다.")
    @PatchMapping("/switch/{likeCommentId}")
    public SuccessResponse<?> switchLikeComment(@RequestBody LikeCommentRequestDto.Switch switchRequest, Long likeCommentId) {
        LikeCommentServiceDto.CommandResponse switchLikeCommentResponse = switchLikeCommentUseCase.execute(switchRequest, likeCommentId);
        return SuccessResponse.of(switchLikeCommentResponse);
    }
}
