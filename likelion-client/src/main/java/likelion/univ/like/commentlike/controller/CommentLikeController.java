package likelion.univ.like.commentlike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.like.commentlike.dto.CommentLikeCreateRequestDto;
import likelion.univ.like.commentlike.usecase.CreateCommentLikeUseCase;
import likelion.univ.like.commentlike.usecase.SwitchCommentLikeUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/likecomments")
@Tag(name = "댓글 좋아요", description = "댓글 좋아요 API")
public class CommentLikeController {
    private final CreateCommentLikeUseCase createCommentLikeUseCase;
    private final SwitchCommentLikeUseCase switchCommentLikeUseCase;

    @Operation(summary = "댓글 좋아요 수행", description = "댓글 좋아요를 생성합니다.")
    @PostMapping("")
    public SuccessResponse<?> createCommentLike(@RequestBody CommentLikeCreateRequestDto request) {
        return createCommentLikeUseCase.execute(request);
    }

    @Operation(summary = "댓글 좋아요 수정", description = "이미 좋아요한 댓글을 취소하거나, 취소한 댓글을 다시 좋아요 합니다.")
    @PatchMapping("/switch/{commentLikeId}")
    public SuccessResponse<?> switchCommentLike(@PathVariable Long commentLikeId) {
        return switchCommentLikeUseCase.execute(commentLikeId);
    }
}
