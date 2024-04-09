package likelion.univ.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.univ.comment.dto.request.CommentCreateChildRequestDto;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.comment.dto.request.CommentUpdateRequestDto;
import likelion.univ.comment.dto.response.CommentResponseDto;
import likelion.univ.comment.service.ClientCommentService;
import likelion.univ.domain.comment.dto.request.DeleteCommentCommand;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
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

    private final AuthenticatedUserUtils userUtils;
    private final ClientCommentService commentService;

    @Operation(summary = "게시글에 대한 댓글 전체 조회",
            description = """
                    ### params
                    - 게시글 id
                    """)
    @GetMapping("/comments")
    public SuccessResponse<List<CommentResponseDto>> getCommentsByPost(
            @RequestParam("postId") Long postId
    ) {
        List<CommentResponseDto> response = commentService.getComments(postId);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/comments/parent")
    public SuccessResponse createParentComment(
            @RequestParam("postId") Long postId,
            @RequestBody CommentCreateParentRequestDto request
    ) {
        Long userId = userUtils.getCurrentUserId();
        commentService.createParentComment(request.toCommand(postId, userId));
        return SuccessResponse.of(null, "201");
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/comments/{parentCommentId}/child")
    public SuccessResponse createChildComment(
            @PathVariable("parentCommentId") Long parentCommentId,
            @RequestBody CommentCreateChildRequestDto request
    ) {
        Long userId = userUtils.getCurrentUserId();
        commentService.createChildComment(request.toCommand(parentCommentId, userId));
        return SuccessResponse.of(null, "201");
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글의 내용(body only)을 수정합니다.")
    @PatchMapping("/{commentId}")
    public SuccessResponse<Long> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequestDto request
    ) {
        Long userId = userUtils.getCurrentUserId();
        Long updatedCommentId = commentService.updateComment(request.toCommand(commentId, userId));
        return SuccessResponse.of(updatedCommentId);
    }

    @Operation(summary = "댓글 soft 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/disable/{commentId}")
    public SuccessResponse deleteCommentSoft(
            @PathVariable("commentId") Long commentId
    ) {
        Long loginUserId = userUtils.getCurrentUserId();
        DeleteCommentCommand command = DeleteCommentCommand.of(commentId, loginUserId);
        commentService.softDeleteComment(command);  // soft delete
        return SuccessResponse.empty();
    }

    @Operation(
            summary = "(시스템용) 댓글 완전 삭제",
            description = """
                    댓글을 hard delete 합니다.
                    (주의) 시스템 관리자용이므로 클라이언트에 노출되지 않도록 합니다.
                    """)
    @DeleteMapping("/{commentId}")
    public SuccessResponse deleteCommentHard(
            @PathVariable("commentId") Long commentId
    ) {
        DeleteCommentCommand command = new DeleteCommentCommand(commentId, userUtils.getCurrentUserId());
        commentService.hardDeleteComment(command);
        return SuccessResponse.empty();
    }
}
