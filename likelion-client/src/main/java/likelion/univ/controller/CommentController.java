package likelion.univ.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.dto.common.CommonResponseDto;
import likelion.univ.domain.repository.CommentRepository;
import likelion.univ.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Comment", description = "댓글 api")
@RestController
@CrossOrigin("*") /* 향후 프론트 IP에 대해서만 열어두도록 개선 필요 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/community/comment")
public class CommentController {
    private final CommentService commentService; // C.U.D.
    private final CommentRepository commentRepository; // READ

    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 작성 완료"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @PostMapping("/parent")
    public ResponseEntity<CommonResponseDto<Object>> createParentComment(@Valid @RequestBody CommentDto.CreateParent request) {
        CommonResponseDto<Object> responseBody = commentService.createParentComment(request);

        return ResponseEntity.ok()
                .body(responseBody);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 작성 완료"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @PostMapping("/child")
    public ResponseEntity<CommonResponseDto<Object>> createChildComment(@RequestBody CommentDto.CreateChild request) {
        CommonResponseDto<Object> responseBody = commentService.createChildComment(request);

        return ResponseEntity.ok()
                .body(responseBody);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글 body필드를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 수정 완료"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @PatchMapping("/update/{commentId}")
    public ResponseEntity<CommonResponseDto<Object>> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentDto.UpdateComment request) {
        CommonResponseDto<Object> responseBody = commentService.updateCommentBody(commentId, request);
        return ResponseEntity.ok()
                .body(responseBody);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 soft delete 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 삭제 완료"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @PatchMapping("/delete/{commentId}")
    public ResponseEntity<CommonResponseDto<Object>> deleteComment(@PathVariable("commentId") Long commentId, @RequestBody CommentDto.DeleteComment request) {
        CommonResponseDto<Object> responseBody = commentService.deleteComment(commentId, request); // soft delete
        return ResponseEntity.ok()
                .body(responseBody);
    }
}
