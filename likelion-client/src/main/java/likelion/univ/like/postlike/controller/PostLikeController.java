package likelion.univ.like.postlike.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.like.postlike.dto.PostLikeRequestDto;
import likelion.univ.like.postlike.usecase.CreateOrDeletePostLikeUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/post-likes")
@Tag(name = "게시글 좋아요", description = "커뮤니티 APIs")
public class PostLikeController {
    private final CreateOrDeletePostLikeUsecase createOrDeletePostLikeUsecase;

    @Operation(summary = "게시글 좋아요 수행", description = "게시글 좋아요를 생성함")
    @PostMapping("")
    public SuccessResponse createOrDeletePostLike(
            @RequestBody PostLikeRequestDto request
    ) {
        boolean likeCreated = createOrDeletePostLikeUsecase.execute(request);
        if (likeCreated) {
            return SuccessResponse.of("좋아요가 생성되었습니다.", "201");
        }
        return SuccessResponse.of("좋아요가 삭제되었습니다.");
    }
}
