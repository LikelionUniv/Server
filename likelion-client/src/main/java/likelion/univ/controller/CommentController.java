package likelion.univ.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.repository.CommentRepository;
import likelion.univ.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment", description = "댓글 api")
@RestController
@CrossOrigin("*") /* 향후 프론트 IP에 대해서만 열어두도록 개선 */
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {
    /* 의존성 추가 예정 */
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Operation(summary = "여기는 api 동작 요약", description = "api 동작 상세 설명")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "good result"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @GetMapping("/community")
    public ResponseEntity<CommentDto.ResponseSave> exampleMethod(String name) {

        return null;
    }
}
