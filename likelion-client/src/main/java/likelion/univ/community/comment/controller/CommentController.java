package likelion.univ.community.comment.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.community.comment.dto.CommentRequestDto;
import likelion.univ.community.comment.usecase.*;
import likelion.univ.domain.community.comment.dto.CommentServiceDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin("*") /* 향후 nginx IP에 대해서만 열어두도록 개선 필요 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/comment")
@Api(tags = {"댓글 API"})
public class CommentController {
    private final CreateParentCommentUseCase createParentCommentUseCase;
    private final CreateChildCommentUseCase createChildCommentUseCase;
    private final EditCommentUseCase editCommentUseCase;
    private final SoftDeleteCommentUseCase softDeleteCommentUseCase;
    private final HardDeleteCommentUseCase hardDeleteCommentUseCase;

    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/parent")
    public SuccessResponse<?> createParentComment(@RequestBody CommentRequestDto.CreateParent request) {
        CommentServiceDto.Response createParentResponse = createParentCommentUseCase.execute(request);
        return SuccessResponse.of(createParentResponse);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/child")
    public SuccessResponse<?> createChildComment(@RequestBody CommentRequestDto.CreateChild request) {
        CommentServiceDto.Response createChildResponse = createChildCommentUseCase.execute(request);
        return SuccessResponse.of(createChildResponse);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글 body필드를 수정합니다.")
    /*
    * patch / put 선택 후 리팩 필요
    **/
    @PatchMapping("/edit")
    public SuccessResponse<?> editComment(@RequestBody CommentRequestDto.EditComment request) {
        CommentServiceDto.Response editResponse = editCommentUseCase.execute(request);
        return SuccessResponse.of(editResponse);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/delete")
    public SuccessResponse<?> deleteComment(@RequestBody CommentRequestDto.DeleteComment request) {
        SuccessResponse<?> deleteResponse = softDeleteCommentUseCase.execute(request);// soft delete
        return SuccessResponse.of(deleteResponse);
    }
}
