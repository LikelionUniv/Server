package likelion.univ.community.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.community.comment.usecase.*;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin("*") /* 향후 nginx IP에 대해서만 열어두도록 개선 필요 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/comment")
@Tag(name = "댓글", description = "댓글 API")
public class CommentApiController {
    private final CreateParentCommentUseCase createParentCommentUseCase;
    private final CreateChildCommentUseCase createChildCommentUseCase;
    private final EditCommentUseCase editCommentUseCase;
    private final SoftDeleteCommentUseCase softDeleteCommentUseCase;
    private final HardDeleteCommentUseCase hardDeleteCommentUseCase;
    private final GetAllCommentUseCase getAllCommentUseCase;

    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/parent")
    public SuccessResponse<?> createParentComment(@RequestBody CommentRequestDto.CreateParent request) {
        CommentServiceDto.CommandResponse createParentCommandResponse = createParentCommentUseCase.execute(request);
        return SuccessResponse.of(createParentCommandResponse);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/child")
    public SuccessResponse<?> createChildComment(@RequestBody CommentRequestDto.CreateChild request) {
        CommentServiceDto.CommandResponse createChildCommandResponse = createChildCommentUseCase.execute(request);
        return SuccessResponse.of(createChildCommandResponse);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글 body필드를 수정합니다.")
    /*
    * patch / put 선택 후 리팩 필요
    **/
    @PatchMapping("/edit")
    public SuccessResponse<?> editComment(@RequestBody CommentRequestDto.EditComment request, @RequestParam Long commentId) {
        CommentServiceDto.CommandResponse editCommandResponse = editCommentUseCase.execute(request, commentId);
        return SuccessResponse.of(editCommandResponse);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/delete/soft")
    public SuccessResponse<?> deleteCommentSoft(@RequestBody CommentRequestDto.DeleteComment request, @RequestParam Long commentId) {
        SuccessResponse<?> deleteResponse = softDeleteCommentUseCase.execute(request, commentId);// soft delete
        return SuccessResponse.of(deleteResponse);
    }

    @Operation(summary = "댓글 완전 삭제", description = "댓글을 hard delete 합니다.")
    @DeleteMapping("/delete/hard")
    public SuccessResponse<?> deleteCommentHard(@RequestBody CommentRequestDto.DeleteComment request, @RequestParam Long commentId) {
        SuccessResponse<?> deleteResponse = hardDeleteCommentUseCase.execute(request, commentId);
        return SuccessResponse.of(deleteResponse);
    }

    @Operation(summary = "댓글 조회", description = "게시글에 대한 댓글을 전부 조회합니다.")
    @GetMapping("/{postId}")
    public SuccessResponse<?> getComments(@PathVariable Long postId) {
        List<CommentServiceDto.ReadResponse> getResponse = getAllCommentUseCase.execute(postId);
        return SuccessResponse.of(getResponse);
    }
}
