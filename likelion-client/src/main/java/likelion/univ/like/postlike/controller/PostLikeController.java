package likelion.univ.like.postlike.controller;


import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.domain.like.postlike.dto.PostLikeResponseDto;
import likelion.univ.like.postlike.dto.PostLikeRequestDto;
import likelion.univ.like.postlike.usecase.CreatePostLikeUseCase;
import likelion.univ.like.postlike.usecase.DeletePostLikeUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/likepost")
public class PostLikeController {
    private final CreatePostLikeUseCase createPostLikeUseCase;
    private final DeletePostLikeUseCase deletePostLikeUseCase;


    @Operation(summary = "게시글 좋아요 수행", description = "게시글 좋아요를 생성함")
    @PostMapping("")
    public SuccessResponse<?> createLikePost(@RequestBody PostLikeRequestDto request) {

        PostLikeResponseDto response = createPostLikeUseCase.execute(request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "게시글 좋아요 삭제", description = "게시글 좋아요를 hard delete함")
    @DeleteMapping("/{postLikeId}")
    public SuccessResponse<?> deleteLikePost(@PathVariable Long postLikeId) {

        return deletePostLikeUseCase.execute(postLikeId);
    }
}
