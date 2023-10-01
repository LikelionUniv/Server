package likelion.univ.like.postlike.controller;


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


    @PostMapping("")
    public SuccessResponse<?> createLikePost(@RequestBody PostLikeRequestDto request) {

        PostLikeResponseDto response = createPostLikeUseCase.execute(request);
        return SuccessResponse.of(response);
    }

    @DeleteMapping("/{postLikeId}")
    public SuccessResponse<?> deleteLikePost(@PathVariable Long postLikeId) {

        return deletePostLikeUseCase.execute(postLikeId);
    }
}
