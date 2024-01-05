package likelion.univ.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.comment.dto.request.CommentUpdateRequestDto;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.comment.usecase.*;
import likelion.univ.domain.comment.dto.response.CommentIdData;
import likelion.univ.domain.comment.dto.response.SimpleCommentData;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private final GetCommentUseCase getCommentUseCase;

    /* read */
    @Operation(summary = "게시글에 대한 댓글 전체 조회",
            description = """
                    ### params
                    - 게시글 id
                    """)
    @GetMapping("/comments")
    public SuccessResponse<List<CommentResponseDto>> getCommentsByPost(@RequestParam Long postId) {
        List<CommentResponseDto> response = getCommentUseCase.execute(postId);
        return SuccessResponse.of(response);
    }

    /* command */
    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/comments/parent")
    public SuccessResponse<SimpleCommentData> createParentComment(@RequestParam Long postId, @RequestBody CommentCreateParentRequestDto request) {
        SimpleCommentData response = createParentCommentUseCase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/comments/{parentCommentId}/child")
    public SuccessResponse<SimpleCommentData> createChildComment(@PathVariable Long parentCommentId, @RequestBody CommentCreateChildRequestDto request) {
        SimpleCommentData response = createChildCommentUseCase.execute(parentCommentId, request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글의 내용(body only)을 수정합니다.")
    @PatchMapping("/{commentId}")
    public SuccessResponse<CommentIdData> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto request) {
        CommentIdData response = updateCommentUseCase.execute(commentId, request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "댓글 soft 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/disable/{commentId}")
    public SuccessResponse<SimpleCommentData> deleteCommentSoft(@PathVariable Long commentId) {
        SimpleCommentData response = softDeleteCommentUseCase.execute(commentId);// soft delete
        return SuccessResponse.of(response);
    }

    @Operation(summary = "(시스템용) 댓글 완전 삭제", description = """
            댓글을 hard delete 합니다.
            (주의) 시스템 관리자용이므로 클라이언트에 노출되지 않도록 합니다.""")

    @DeleteMapping("/{commentId}")
    public SuccessResponse<?> deleteCommentHard(@PathVariable Long commentId) {
        hardDeleteCommentUseCase.execute(commentId);
        return SuccessResponse.empty();
    }
}
