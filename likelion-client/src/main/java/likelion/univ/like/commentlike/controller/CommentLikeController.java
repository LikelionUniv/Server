package likelion.univ.like.commentlike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.like.commentlike.dto.CommentLikeRequestDto;
import likelion.univ.like.commentlike.usecase.CreateOrDeleteCommentLikeUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/comment-likes")
@Tag(name = "댓글 좋아요", description = "커뮤니티 APIs")
public class CommentLikeController {
    private final CreateOrDeleteCommentLikeUsecase createOrDeleteCommentLikeUsecase;

    @Operation(summary = "댓글 좋아요 수행 / 취소", description = """
            - commentId에 대하여 댓글 좋아요 수행
            - 만약 이미 좋아요를 했으면 좋아요 hard delete 수행
            """)
    @PostMapping("")
    public SuccessResponse createOrDeleteCommentLike(
            @RequestBody CommentLikeRequestDto request
    ) throws NotAuthorizedException {
        boolean likeCreated = createOrDeleteCommentLikeUsecase.execute(request);
        if (likeCreated) {
            return SuccessResponse.of("좋아요가 생성되었습니다.", "201");
        }
        return SuccessResponse.of("좋아요가 취소되었습니다.");
    }
}
