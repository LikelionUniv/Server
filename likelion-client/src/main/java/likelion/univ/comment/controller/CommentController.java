package likelion.univ.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.comment.dto.request.CommentUpdateRequestDto;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.comment.usecase.CreateChildCommentUsecase;
import likelion.univ.comment.usecase.CreateParentCommentUsecase;
import likelion.univ.comment.usecase.GetCommentUsecase;
import likelion.univ.comment.usecase.HardDeleteCommentUsecase;
import likelion.univ.comment.usecase.SoftDeleteCommentUsecase;
import likelion.univ.comment.usecase.UpdateCommentUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community")
@Tag(name = "댓글", description = "커뮤니티 APIs")
public class CommentController {
    private final CreateParentCommentUsecase createParentCommentUsecase;
    private final CreateChildCommentUsecase createChildCommentUsecase;
    private final UpdateCommentUsecase updateCommentUsecase;
    private final SoftDeleteCommentUsecase softDeleteCommentUsecase;
    private final HardDeleteCommentUsecase hardDeleteCommentUsecase;
    private final GetCommentUsecase getCommentUsecase;

    /* read */
    @Operation(summary = "게시글에 대한 댓글 전체 조회",
            description = """
                    ### params
                    - 게시글 id
                    """)
    @GetMapping("/comments")
    public SuccessResponse<List<CommentResponseDto>> getCommentsByPost(
            @RequestParam Long postId
    ) {
        List<CommentResponseDto> response = getCommentUsecase.execute(postId);
        return SuccessResponse.of(response);
    }

    /* command */
    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/comments/parent")
    public SuccessResponse createParentComment(
            @RequestParam Long postId,
            @RequestBody CommentCreateParentRequestDto request
    ) {
        createParentCommentUsecase.execute(postId, request);
        return SuccessResponse.of(null, "201");
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/comments/{parentCommentId}/child")
    public SuccessResponse createChildComment(
            @PathVariable Long parentCommentId,
            @RequestBody CommentCreateChildRequestDto request
    ) {
        createChildCommentUsecase.execute(parentCommentId, request);
        return SuccessResponse.of(null, "201");
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글의 내용(body only)을 수정합니다.")
    @PatchMapping("/{commentId}")
    public SuccessResponse<Long> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto request
    ) {
        Long updatedCommentId = updateCommentUsecase.execute(commentId, request);
        return SuccessResponse.of(updatedCommentId);
    }

    @Operation(summary = "댓글 soft 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/disable/{commentId}")
    public SuccessResponse deleteCommentSoft(
            @PathVariable Long commentId
    ) {
        softDeleteCommentUsecase.execute(commentId);// soft delete
        return SuccessResponse.empty();
    }

    @Operation(summary = "(시스템용) 댓글 완전 삭제", description = """
            댓글을 hard delete 합니다.
            (주의) 시스템 관리자용이므로 클라이언트에 노출되지 않도록 합니다.""")

    @DeleteMapping("/{commentId}")
    public SuccessResponse deleteCommentHard(
            @PathVariable Long commentId
    ) {
        hardDeleteCommentUsecase.execute(commentId);
        return SuccessResponse.empty();
    }
}
