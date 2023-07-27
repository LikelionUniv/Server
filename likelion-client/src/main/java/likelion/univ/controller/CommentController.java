package likelion.univ.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment", description = "댓글 api")
@RequestMapping("v1")
@RestController
public class CommentController {
    @Operation(summary = "여기는 api 동작 요약", description = "api 동작 상세 설명")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "good result"),
            @ApiResponse(responseCode = "400", description = "client error"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @GetMapping("hello")
    public String hello(String name) {
        return new StringBuffer("hello").append(name).toString();
    }
}
