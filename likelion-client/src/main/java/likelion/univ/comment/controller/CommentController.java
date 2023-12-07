package likelion.univ.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.comment.dto.request.CommentUpdateRequestDto;
import likelion.univ.comment.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community")
@Tag(name = "댓글", description = "커뮤니티 APIs")
public class CommentController {
    private final CreateParentCommentUseCase createParentCommentUseCase;
    private final CreateChildCommentUseCase createChildCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final SoftDeleteCommentUseCase softDeleteCommentUseCase;
    private final HardDeleteCommentUseCase hardDeleteCommentUseCase;


    /* command */
    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/comments/parent")
    public SuccessResponse<?> createParentComment(@RequestParam Long postId, @RequestBody CommentCreateParentRequestDto request) {
        return createParentCommentUseCase.execute(postId, request);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/comments/{parentCommentId}/child")
    public SuccessResponse<?> createChildComment(@PathVariable Long parentCommentId, @RequestBody CommentCreateChildRequestDto request) {
        return createChildCommentUseCase.execute(parentCommentId, request);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글의 내용(body only)을 수정합니다.")
    @PatchMapping("/{commentId}")
    public SuccessResponse<?> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto request) {
        return updateCommentUseCase.execute(commentId, request);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/disable/{commentId}")
    public SuccessResponse<?> deleteCommentSoft(@PathVariable Long commentId) {
        return softDeleteCommentUseCase.execute(commentId);// soft delete
    }

    @Operation(summary = "댓글 완전 삭제", description = "댓글을 hard delete 합니다.")
    @DeleteMapping("/{commentId}")
    public SuccessResponse<?> deleteCommentHard(@PathVariable Long commentId) {
        return hardDeleteCommentUseCase.execute(commentId);
    }
}
