package likelion.univ.community.likepost.controller;


import likelion.univ.community.likepost.dto.LikePostRequestDto;
import likelion.univ.community.likepost.usecase.CreateLikePostUseCase;
import likelion.univ.community.likepost.usecase.DeleteLikePostUseCase;

import likelion.univ.domain.community.likepost.dto.LikePostDto;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/community/likepost")
public class LikePostController {
    @Autowired
    private CreateLikePostUseCase createLikePostUseCase;
    @Autowired
    private DeleteLikePostUseCase deleteLikePostUseCase;

    @PostMapping("")
    public SuccessResponse createLikePost(@RequestBody LikePostRequestDto.Save request) {

        LikePostDto.ResponseDTO response = createLikePostUseCase.execute(request);

        return SuccessResponse.of(response);
    }

    @DeleteMapping("")
    public SuccessResponse deleteLikePost(@RequestBody LikePostRequestDto.Delete request) {

        deleteLikePostUseCase.execute(request);

        return SuccessResponse.empty();
    }
}
