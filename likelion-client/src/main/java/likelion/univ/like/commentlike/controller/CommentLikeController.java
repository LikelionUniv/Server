package likelion.univ.like.commentlike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.like.commentlike.dto.CommentLikeResponseDto;
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
@RequestMapping("/v1/community/commentlike")
@Tag(name = "댓글 좋아요", description = "커뮤니티 APIs")
public class CommentLikeController {
    private final CreateCommentLikeUseCase createCommentLikeUseCase;
    private final SwitchCommentLikeUseCase switchCommentLikeUseCase;

    @Operation(summary = "댓글 좋아요 수행", description = "댓글 좋아요를 생성합니다.")
    @PostMapping("")
    public SuccessResponse<?> createCommentLike(@RequestBody CommentLikeCreateRequestDto request) {
        CommentLikeResponseDto response = createCommentLikeUseCase.execute(request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "댓글 좋아요 수정", description = "좋아요 상태를 bool 필드를 이용해 on/off 전환합니다. (soft switch)")
    @PatchMapping("/switch/{commentLikeId}")
    public SuccessResponse<?> switchCommentLike(@PathVariable Long commentLikeId) {
        return switchCommentLikeUseCase.execute(commentLikeId);
    }
}
