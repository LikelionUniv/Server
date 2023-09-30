package likelion.univ.likepost.controller;


import likelion.univ.domain.likepost.dto.LikePostResponseDto;
import likelion.univ.likepost.dto.LikePostRequestDto;
import likelion.univ.likepost.usecase.CreateLikePostUseCase;
import likelion.univ.likepost.usecase.DeleteLikePostUseCase;
import likelion.univ.domain.likepost.dto.LikePostCreateRequestDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/community/likepost")
public class LikePostController {
    private final CreateLikePostUseCase createLikePostUseCase;
    private final DeleteLikePostUseCase deleteLikePostUseCase;


    @PostMapping("")
    public SuccessResponse createLikePost(@RequestBody LikePostRequestDto request) {

        LikePostResponseDto response = createLikePostUseCase.execute(request);

        return SuccessResponse.of(response);
    }

    @DeleteMapping("")
    public SuccessResponse deleteLikePost(@RequestBody LikePostRequestDto request) {

        deleteLikePostUseCase.execute(request);

        return SuccessResponse.empty();
    }
}
