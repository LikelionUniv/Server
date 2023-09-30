package likelion.univ.commentlike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.commentlike.dto.CommentLikeRequestDto;
import likelion.univ.commentlike.usecase.CreateCommentLikeUseCase;
import likelion.univ.commentlike.usecase.SwitchCommentLikeUseCase;
import likelion.univ.domain.commentlike.dto.CommentLikeServiceDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/likecomment")
@Tag(name = "댓글 좋아요", description = "댓글 좋아요 API")
public class CommentLikeController {
    private final CreateCommentLikeUseCase createCommentLikeUseCase;
    private final SwitchCommentLikeUseCase switchCommentLikeUseCase;

    @Operation(summary = "댓글 좋아요 수행", description = "댓글 좋아요를 생성합니다.")
    @PostMapping("/new")
    public SuccessResponse<?> createLikeComment(@RequestBody CommentLikeRequestDto.Create createRequest) {
        CommentLikeServiceDto.CommandResponse createLikeCommentResponse = createCommentLikeUseCase.execute(createRequest);
        return SuccessResponse.of(createLikeCommentResponse);
    }

    @Operation(summary = "댓글 좋아요 수정", description = "이미 좋아요한 댓글을 취소하거나, 취소한 댓글을 다시 좋아요 합니다.")
    @PatchMapping("/switch/{likeCommentId}")
    public SuccessResponse<?> switchLikeComment(@RequestBody CommentLikeRequestDto.Switch switchRequest, Long likeCommentId) {
        CommentLikeServiceDto.CommandResponse switchLikeCommentResponse = switchCommentLikeUseCase.execute(switchRequest, likeCommentId);
        return SuccessResponse.of(switchLikeCommentResponse);
    }
}
